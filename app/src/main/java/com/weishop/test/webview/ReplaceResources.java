package com.weishop.test.webview;

import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.SystemClock;
import android.util.TypedValue;

public class ReplaceResources extends Resources {
    static final TypedValue typedValue = new TypedValue();

    private Resources system;

    public ReplaceResources(Resources system) {
        super(system.getAssets(), system.getDisplayMetrics(), system.getConfiguration());
        this.system = system;
    }


    @Override
    public int getInteger(int id) {

        try {
            return super.getInteger(id);
        } catch (NotFoundException e) {
            return system.getInteger(id);
        }

    }

    @Override
    public XmlResourceParser getLayout(int id) {
        NotFoundException error = null;
        XmlResourceParser parser = null;

        try {
            parser = system.getLayout(id);
        } catch (NotFoundException e) {
            error = e;
        }

        if (parser == null) {
            try {
                parser = super.getLayout(id);
                error = null;
            } catch (NotFoundException e) {
                error = e;
            }
        }

        if (parser != null) {
            return parser;
        }

        String name = Integer.toHexString(id);

        try {
            TypedValue typedValue = new TypedValue();
            getValue(id, typedValue, true);
            name = typedValue.string.toString();
        } catch (NotFoundException e) {
            e.printStackTrace();
        }

        SystemClock.sleep(3000);

        throw error;
    }

    @Override
    public void getValue(int id, TypedValue outValue, boolean resolveRefs) {

        try {
            super.getValue(id, outValue, resolveRefs);
        } catch (NotFoundException e) {
            system.getValue(id, outValue, resolveRefs);
        }

    }

    public static String getResName(Resources res, int id) {
        String name = null;

        synchronized (typedValue) {
            res.getValue(id, typedValue, true);
            CharSequence string = typedValue.string;
            if (string != null) {
                name = string.toString();
            }
        }

        return name;
    }

}

