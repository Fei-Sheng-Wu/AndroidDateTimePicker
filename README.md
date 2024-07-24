# AndroidDateTimePicker

[![Min SDK](https://img.shields.io/badge/Min%20SDK-5.0-green.svg?style=flat-square)](https://developer.android.com/studio/releases/platforms#5.0)
[![Lincense](https://img.shields.io/badge/Lincense-Apache%202.0%20License-orange.svg?style=flat-square)](https://github.com/Fei-Sheng-Wu/AndroidDateTimePicker/blob/1.0.0/LICENSE.txt)

> A date & time picker component for Android. Light themed design, fullscreen picker dialog, and native Material components only. Support setting default selected date & time, and selection result callback handler. Support custom button icon, title text, and colors. Minimum dependency of SDK 5.0.

## Dependencies

**Min SDK** = 5.0

## Main Features

- [x] Date & time picker
- [x] Default selected date & time
- [x] Selection result callback listener
- [x] Custom button icons, title text, and colors

## Preview

![DateTimePicker Select Date](https://github.com/Fei-Sheng-Wu/AndroidDateTimePicker/blob/792d5f606c38348cc9a916268959a941ef347538/Screenshot/DateTimePicker%20Select%20Date.png)
![DateTimePicker Select Time](https://github.com/Fei-Sheng-Wu/AndroidDateTimePicker/blob/792d5f606c38348cc9a916268959a941ef347538/Screenshot/DateTimePicker%20Select%20Time.png)

## How to Use

It is very simple to set up.

```java
DateTimePickerDialog selectDialog = new DateTimePickerDialog();
selectDialog.setDefaultDateTime(Calendar.getInstance()); //Set default selected date & time.
selectDialog.setOnResultsListener(new DateTimePickerDialog.OnResultsListener() {
    @Override
    public void onSuccess(Calendar date) {
        //Selection result callback. Parameter "date" holds the selected date & time.
    }
});
getSupportFragmentManager().beginTransaction()
    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN) //Set the transition animation when dialog opens.
    .add(R.id.root_view, selectDialog) //Show dialog. "R.id.root_view" should be replaced by the ID of the activity's root view.
    .addToBackStack(null) //Add dialog to back stack.
    .commit();
```

(Optional) Add these to string.xmls to change the title text.

```xml
<string name="title_select_date">Select Date</string>
<string name="title_select_time">Select Time</string>
```

(Optional) Add these to colors.xmls to change the colors.

```xml
<color name="colorPrimary">#FFFFFF</color>
<color name="colorPrimaryDark">#FFFFFF</color>
<color name="colorAccent">#000000</color>
<color name="colorControl">#EDEDED</color>
```

(Optional) Add these drawables to the drawable directory to change button icons.

|Drawable Name|Used For|Icon Size|
|--------   |--------   |--------   |
|ic_close_24.xml|Close picker dialog for canceling selection button. |24dp x 24dp|
|ic_next_24.xml|Switch from date selection to time selection button.|24dp x 24dp|
|ic_submit_24.xml|Submit selected result button.|24dp x 24dp|

## License

This project is under the [Apache 2.0 License](https://github.com/Fei-Sheng-Wu/AndroidDateTimePicker/blob/1.0.0/LICENSE.txt).
