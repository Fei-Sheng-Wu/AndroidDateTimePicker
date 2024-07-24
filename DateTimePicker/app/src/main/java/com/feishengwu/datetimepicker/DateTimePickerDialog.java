package com.feishengwu.datetimepicker;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.ViewSwitcher;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.Calendar;
import java.util.Date;

public class DateTimePickerDialog extends DialogFragment {
    private OnResultsListener onResultsListener;
    private Calendar defaultTime;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.dialog_date_time_picker, container, false);

        final MaterialToolbar toolbar = (MaterialToolbar) root.findViewById(R.id.toolbar);
        final ViewSwitcher viewSwitcher = (ViewSwitcher) root.findViewById(R.id.view_switcher);
        final DatePicker datePicker = (DatePicker) root.findViewById(R.id.date_picker);
        final TimePicker timePicker = (TimePicker) root.findViewById(R.id.time_picker);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        toolbar.getMenu().findItem(R.id.action_next).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                toolbar.setTitle(R.string.title_select_time);
                toolbar.getMenu().clear();
                toolbar.inflateMenu(R.menu.toolbar_submit);
                toolbar.getMenu().findItem(R.id.action_submit).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (onResultsListener != null) {
                            Calendar dateTime = Calendar.getInstance();
                            dateTime.set(Calendar.YEAR, datePicker.getYear());
                            dateTime.set(Calendar.MONTH, datePicker.getMonth());
                            dateTime.set(Calendar.DAY_OF_MONTH, datePicker.getDayOfMonth());
                            dateTime.set(Calendar.HOUR_OF_DAY, timePicker.getCurrentHour());
                            dateTime.set(Calendar.MINUTE, timePicker.getCurrentMinute());

                            onResultsListener.onSuccess(dateTime);
                        }

                        dismiss();

                        return false;
                    }
                });

                viewSwitcher.showNext();

                return false;
            }
        });

        if (defaultTime != null) {
            datePicker.updateDate(defaultTime.get(Calendar.YEAR), defaultTime.get(Calendar.MONTH), defaultTime.get(Calendar.DAY_OF_MONTH));
            timePicker.setCurrentHour(defaultTime.get(Calendar.HOUR_OF_DAY));
            timePicker.setCurrentMinute(defaultTime.get(Calendar.MINUTE));
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
        void onSuccess(Calendar dateTime);
    }
}
