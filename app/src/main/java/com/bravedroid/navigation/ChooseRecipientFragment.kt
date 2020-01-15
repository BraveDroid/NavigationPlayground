package com.bravedroid.navigation


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bravedroid.navigation.util.navigateWithAnimation
import kotlinx.android.synthetic.main.fragment_choose_recipient.*

class ChooseRecipientFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_choose_recipient, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        next_btn_choose_recipient.setOnClickListener {
            val name = input_recipient.text.toString()
            findNavController().navigateWithAnimation(
                ChooseRecipientFragmentDirections.actionChooseRecipientFragmentToSpecifyAmountFragment(
                    name
                )
            )
        }
        info_btn.setOnClickListener {
            findNavController().navigateWithAnimation(ChooseRecipientFragmentDirections.actionGlobalInfoFragment())
        }

        cancel_btn.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}
