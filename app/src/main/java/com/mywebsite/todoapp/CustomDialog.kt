package com.mywebsite.todoapp

import android.app.ActionBar
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.mywebsite.todoapp.data.PrefsRepositoryImpl.Companion.PREFS_DESCRIPTION_KEY
import com.mywebsite.todoapp.data.PrefsRepositoryImpl.Companion.PREFS_TITLE_KEY
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CustomDialog(
    private val isNewItem: Boolean,
    private val item: ToDoItem?
) : DialogFragment(), View.OnClickListener {

    private val customDialogViewModel: CustomDialogViewModel by activityViewModels()
    private val mainViewModel: MainViewModel by activityViewModels()

    private lateinit var okButton: Button
    private lateinit var cancelButton: Button

    private lateinit var inputFieldTitle: EditText
    private lateinit var inputFieldDescription: EditText
    private lateinit var dialogLabel: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view: View = inflater.inflate(R.layout.dialog_template, container, false)

        initViews(view)

        if (isNewItem) {
            customDialogViewModel.getToDoItemFromPrefs()
        } else {
            dialogLabel.text = getString(R.string.update_item)
            inputFieldTitle.setText(item?.title)
            inputFieldDescription.setText(item?.description)
        }
        return view
    }

    override fun onResume() {
        super.onResume()
        dialogSizeControl()
        observers()
    }

    private fun observers() {
        customDialogViewModel.todoItemResult.observe(this, Observer {
            if (isNewItem) {
                inputFieldTitle.setText(it.title)
                inputFieldDescription.setText(it.description)
            }
        })
    }

    private fun initViews(view: View) {
        inputFieldTitle = view.findViewById(R.id.dialog_input_title)
        inputFieldDescription = view.findViewById(R.id.dialog_input_description)
        dialogLabel = view.findViewById(R.id.dialog_label)
        okButton = view.findViewById<Button>(R.id.dialog_ok_button)
        cancelButton = view.findViewById<Button>(R.id.dialog_cancel_button)
        okButton.setOnClickListener(this)
        cancelButton.setOnClickListener(this)
    }

    /**
     * This need to control dialog size
     */
    private fun dialogSizeControl() {
        val params: ViewGroup.LayoutParams = dialog!!.window!!.attributes
        params.width = ActionBar.LayoutParams.MATCH_PARENT
        params.height = ActionBar.LayoutParams.WRAP_CONTENT
        dialog!!.window!!.attributes = params as WindowManager.LayoutParams
    }

    /**
     * This function handles clicks
     */
    override fun onClick(view: View) {
        when (view.id) {
            R.id.dialog_ok_button -> {
                okButtonClicker()
            }
            R.id.dialog_cancel_button -> {
                dismiss()
            }
            else -> {
                elseBeenClicked()
            }
        }
    }

    private fun elseBeenClicked() {
        TODO("Not yet implemented")
    }

    private fun okButtonClicker() {
        if (isNewItem) {
            okNewItemBeenClicked()
        } else {
            okUpdateItemBeenClicked()
        }
        dismiss()
    }

    private fun okUpdateItemBeenClicked() {
        val inputTitleResult = inputFieldTitle.text.toString()
        val inputDescriptionResult = inputFieldDescription.text.toString()
        item?.id?.let { ToDoItem(it, inputTitleResult, inputDescriptionResult) }
            ?.let { mainViewModel.updateItem(it) }
    }

    private fun okNewItemBeenClicked() {
        val inputTitleResult = inputFieldTitle.text.toString()
        val inputDescriptionResult = inputFieldDescription.text.toString()
        mainViewModel.insertItem(ToDoItem(0, inputTitleResult, inputDescriptionResult))
        inputFieldTitle.text.clear()
        inputFieldDescription.text.clear()
    }

    override fun onStop() {
        super.onStop()
        if (isNewItem) {
            val inputTitleResult = inputFieldTitle.text.toString()
            val inputDescriptionResult = inputFieldDescription.text.toString()
            customDialogViewModel.saveDataInPrefs(PREFS_TITLE_KEY, inputTitleResult)
            customDialogViewModel.saveDataInPrefs(PREFS_DESCRIPTION_KEY, inputTitleResult)
        }
    }
}