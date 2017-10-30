package ua.i.pl.sosnovskyi.googleplusclone;

import android.app.Dialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;



public class MyDialog extends android.support.v4.app.DialogFragment {
private int position;
    final String[] dialogItemNamesArray = {"Edit", "Delete", "Cancel"};
    public MyDialog() {
        position=0;
    }
    public void setPosition(int position){
        this.position=position;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getString(R.string.action_dialog))
                .setItems(dialogItemNamesArray, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        switch (dialogItemNamesArray[which]){
                            case  "Edit":{
                                ((MainActivity) getActivity()).EditClicked(position);
                                break;
                            }
                            case "Delete":{
                                ((MainActivity) getActivity()).DeleteClicked(position);
                                break;
                            }
                            case "Cancel":{
                                break;
                            }
                        }

                    }
                });
        return builder.create();
    }
}
