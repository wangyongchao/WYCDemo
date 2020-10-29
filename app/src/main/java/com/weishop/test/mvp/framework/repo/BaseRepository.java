package com.weishop.test.mvp.framework.repo;

import android.content.Context;

import com.weishop.test.mvp.framework.utils.ReflectTypeUtils;
import com.weishop.test.mvp.framework.annotation.AutoParser;
import com.weishop.test.util.LogUtils;

import java.lang.reflect.Type;
import java.util.Map;

import javax.security.auth.callback.Callback;

/**
 * model层基类
 */
public abstract class BaseRepository implements IRepository {
    private final IHttpRequest mHttpRequest;

    protected final Context mContext;

    public BaseRepository(Context context) {
        mContext = context;
        mHttpRequest = new BaseHttpBusinessWrapper(context);
    }

    @SuppressWarnings({"unchecked"})
    protected final <R, F> void sendPostAutoParser(final String url,
                                                   final Map<String, String> httpRequestParams,
                                                   final IRepository.DataCallBack<R> callBack) throws Exception {

        if (callBack == null) {
            //TODO  throw RuntimeException
            return;
        }

        try {
            String jsonResult = "";
            IParser<R> iParser = null;
            //TODO type == Object.class
            Type type = ReflectTypeUtils.getParameterizedType(Callback.class,
                    callBack.getClass(), 0);
            AutoParser autoParser = callBack.getClass().getAnnotation(AutoParser.class);
            if (autoParser == null) {
                //TODO cache\clear GsonParser
                iParser = new GsonParser((Class) type);
            } else {
                //TODO check ? extends IParser
                Class<? extends IParser> clzParser = autoParser.value();
                //TODO cache\clear clzParser
                iParser = clzParser.newInstance();
            }
            R r = iParser.parse(jsonResult);
            callBack.onSuccess(r);
        } catch (Exception e) {
            //TODO
            F f = null;
            callBack.onFail(null);
        } finally {

        }
    }

    protected final void sendPost(final String url, final Map<String,String> httpRequestParams,
                                  final IParser parser,
                                  final IRepository.DataCallBack dataCallBack) {
        mHttpRequest.sendPost(url, httpRequestParams, new CallBack.DefaultCommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                onRequestSuccess(result, parser, dataCallBack);
            }

            @Override
            public void onFailure(String msg) {
                onRequestFail(msg, dataCallBack);
            }

            @Override
            public void onCancel() {
                super.onCancel();
                onRequestCancel(dataCallBack);
            }
        });
    }


    protected <Model> IParser<Model> getParser(Class<Model> modelClass) {
        return new GsonParser<Model>(modelClass);
    }

    private void onRequestSuccess(String result, IParser parser, IRepository.DataCallBack
            dataCallBack) {
        if (parser != null) {
            try {
                Object parse = parser.parse(result);
                if (dataCallBack != null) {
                    dataCallBack.onSuccess(parse);
                }
            } catch (Exception e) {
                e.printStackTrace();
                onRequestFail("parse data fail " + e.getMessage(), dataCallBack);
            }
        } else {
            onRequestFail("parser should not be empty", dataCallBack);
        }
    }

    /**
     * 根据实体类分析出解析器
     *
     * @param dataCallBack
     * @return
     */
    private IParser analysisParser(DataCallBack dataCallBack) {
        Type type = ReflectTypeUtils.getParameterizedType(dataCallBack.getClass(),
                DataCallBack.class, 0);
        Class typeClass = (Class) type;
        AutoParser autoParser = (AutoParser) typeClass.getAnnotation(AutoParser.class);
        IParser iParser = null;
        if (autoParser == null) {
            iParser = new GsonParser(typeClass);
        } else {
            Class<? extends IParser> clzParser = autoParser.value();
            try {
                iParser = clzParser.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return iParser;
    }


    private void onRequestFail(String msg, IRepository.DataCallBack dataCallBack) {
        LogUtils.e("request onFailure msg=" + msg);
        if (dataCallBack != null) {
            RequestFail requestFail = new RequestFail();
            requestFail.setErroMsg(msg);
            dataCallBack.onFail(requestFail);
        }
    }

    private void onRequestCancel(IRepository.DataCallBack dataCallBack) {
        LogUtils.d("request onCancel");
        if (dataCallBack != null) {
            dataCallBack.onCancel();
        }
    }

    protected <Model> IParser<Model> createParser(Class<Model> modelClass) {
        return new GsonParser<Model>(modelClass);
    }

}
