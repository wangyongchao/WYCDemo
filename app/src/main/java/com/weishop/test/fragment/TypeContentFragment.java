
package com.weishop.test.fragment;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.weishop.test.R;

public class TypeContentFragment extends Fragment implements SubTypeFragment.OnItemClickListener {


    private LinearLayout mTitleLayout;
    private LayoutInflater mInflater;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mInflater = LayoutInflater.from(getActivity());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cate, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTitleLayout = (LinearLayout) view.findViewById(R.id.title_layout);

        TextView textView= (TextView) mInflater.inflate(android.R.layout.simple_list_item_1,null);
        textView.setTag("");

        SubTypeFragment subTypeFragment = new SubTypeFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type", "书籍");
        subTypeFragment.setArguments(bundle);
        subTypeFragment.setOnItemListener(this);
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.content_cate, subTypeFragment);
        fragmentTransaction.commitAllowingStateLoss();


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onItemClick(Bundle bundle) {
        SubTypeFragment subTypeFragment = new SubTypeFragment();
        subTypeFragment.setArguments(bundle);
        subTypeFragment.setOnItemListener(this);
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_cate, subTypeFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commitAllowingStateLoss();
    }

    public boolean onBackPressed() {
        int entryCount = getChildFragmentManager().getBackStackEntryCount();
        if (entryCount > 0) {
            while(entryCount>0){
                getChildFragmentManager().popBackStackImmediate();
                entryCount = getChildFragmentManager().getBackStackEntryCount();
            }
            return true;
        }
        return false;
    }
}
