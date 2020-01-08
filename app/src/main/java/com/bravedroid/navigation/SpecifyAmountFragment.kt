package com.bravedroid.navigation


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bravedroid.navigation.util.navigateWithAnimation
import kotlinx.android.synthetic.main.fragment_specify_amount.*

class SpecifyAmountFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_specify_amount, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val specifyAmountFragmentArgs by navArgs<SpecifyAmountFragmentArgs>()
        val name = specifyAmountFragmentArgs.name
        send_btn.setOnClickListener {
            val amount = input_amount.text.toString()
            findNavController().navigateWithAnimation(
                SpecifyAmountFragmentDirections.actionSpecifyAmountFragmentToConfirmationFragment(
                    amount,
                    name
                )
            )
        }

        cancel_btn.setOnClickListener {
            findNavController().popBackStack(R.id.chooseRecipientFragment, false)
//            same as
//            findNavController().popBackStack()
        }
    }
}
