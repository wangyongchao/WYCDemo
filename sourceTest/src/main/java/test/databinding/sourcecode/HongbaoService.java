package test.databinding.sourcecode;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.List;



public class HongbaoService extends AccessibilityService {
    private  String[] filter=new String[]{"恭喜发财"};
    AccessibilityNodeInfo rootNodeInfo=null;
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
       rootNodeInfo=event.getSource();
        if(rootNodeInfo==null)
        {
            return;
        }
        startClick(rootNodeInfo);
    }

    private void startClick(AccessibilityNodeInfo rootNodeInfo) {
        List<AccessibilityNodeInfo>  list=findByText(rootNodeInfo);
        if(list==null)
        {
            return;
        }
        AccessibilityNodeInfo nodeInfo=list.get(list.size()-1);
        if(nodeInfo!=null)
        {
            if("已拆�?".equals(nodeInfo.getText()))
            {
                return;
            }
            boolean isClick=nodeInfo.getParent().performAction(AccessibilityNodeInfo.ACTION_CLICK);
            if(!isClick)
            {
                recleClick(rootNodeInfo);
            }

        }

    }

    private void recleClick(AccessibilityNodeInfo rootNodeInfo) {
        int childCount=rootNodeInfo.getChildCount();
        if(childCount==0)
        {
            rootNodeInfo.performAction(AccessibilityNodeInfo.ACTION_CLICK);
        }
        for (int i=0;i<childCount;i++)
        {
            AccessibilityNodeInfo info=rootNodeInfo.getChild(i);
            if(info==null)
            {
                continue;
            }
            if(info.getChildCount()>0)
            {
                recleClick(info);
            }else {
                rootNodeInfo.performAction(AccessibilityNodeInfo.ACTION_CLICK);
            }

        }
    }

    private List<AccessibilityNodeInfo> findByText(AccessibilityNodeInfo rootNodeInfo) {
        for (String name:filter)
        {

            List<AccessibilityNodeInfo> list=rootNodeInfo.findAccessibilityNodeInfosByText(name);
            if(list!=null&&!list.isEmpty())
            {
                return list;
            }

        }

        return null;

    }

    @Override
    public void onInterrupt() {

    }
    
    @Override
    protected void onServiceConnected() {
    	// TODO Auto-generated method stub
    	super.onServiceConnected();
    }
}
