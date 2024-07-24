package com.feishengwu.datetimepicker;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.Calendar;
import java.util.Date;

public class DatePickerDialog extends DialogFragment {
    private OnResultsListener onResultsListener;
    private Calendar defaultTime;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.dialog_date_picker, container, false);

        final MaterialToolbar toolbar = (MaterialToolbar) root.findViewById(R.id.toolbar);
        final DatePicker datePicker = (DatePicker) root.findViewById(R.id.date_picker);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        toolbar.getMenu().findItem(R.id.action_submit).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (onResultsListener != null) {
                    Calendar date = Calendar.getInstance();
                    date.set(Calendar.YEAR, datePicker.getYear());
                    date.set(Calendar.MONTH, datePicker.getMonth());
                    date.set(Calendar.DAY_OF_MONTH, datePicker.getDayOfMonth());

                    onResultsListener.onSuccess(date);
                }

                dismiss();

                return false;
            }
        });

        if (defaultTime != null) {
            datePicker.updateDate(defaultTime.get(Calendar.YEAR), defaultTime.get(Calendar.MONTH), defaultTime.get(Calendar.DAY_OF_MONTH));
        }

        return root;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new Dialog(requireContext(), getTheme()) {
            @Override
            public void onBackPressed() {
                dismiss();
            }
        };
    }

    @Override
    public void dismiss() {
        requireActivity().getSupportFragmentManager().beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE).remove(this).commit();
    }

    public void setOnResultsListener(OnResultsListener onResultsListener) {
        this.onResultsListener = onResultsListener;
    }

    public void setDefaultDateTime(Calendar dateTime) {
        defaultTime = dateTime;
    }

    public void setDefaultDateTime(Date dateTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateTime);

        setDefaultDateTime(calendar);
    }

    public interface OnResultsListener {
        void onSuccess(Calendar date);
    }
}
