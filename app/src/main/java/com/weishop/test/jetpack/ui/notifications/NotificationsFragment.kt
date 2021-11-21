package com.weishop.test.jetpack.ui.notifications

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.weishop.test.R
import com.weishop.test.util.LogUtils

class NotificationsFragment : Fragment() {

    private lateinit var notificationsViewModel: NotificationsViewModel


    override fun onAttach(context: Context) {
        super.onAttach(context)
        LogUtils.d("NotificationsFragment onAttach $this")
    }

    override fun onDetach() {
        super.onDetach()
        LogUtils.d("NotificationsFragment onDetach $this")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogUtils.d("NotificationsFragment onCreate $this")
    }

    override fun onDestroy() {
        super.onDestroy()
        LogUtils.d("NotificationsFragment onDestroy $this")
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
                ViewModelProvider(this).get(NotificationsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_notifications, container, false)
        val textView: TextView = root.findViewById(R.id.text_notifications)
        notificationsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}