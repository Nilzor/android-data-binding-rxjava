package agency.tango.databindingrxjava;

import android.databinding.DataBindingUtil;
import android.databinding.Observable.OnPropertyChangedCallback;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import agency.tango.databindingrxjava.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setViewModel(new MainViewModel());
        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
    }

    public static class MainViewModel {

        public ObservableField<String> firstName = new ObservableField<>();
        public ObservableField<String> lastName = new ObservableField<>();
        public ObservableField<String> helloText = new ObservableField<>();
        public ObservableBoolean helloButtonEnabled = new ObservableBoolean(false);

        public MainViewModel() {
            OnPropertyChangedCallback buttonUpdater = new BindingCallbackAdapter(this::updateButtonEnabledState);
            firstName.addOnPropertyChangedCallback(buttonUpdater);
            lastName.addOnPropertyChangedCallback(buttonUpdater);
        }

        private void updateButtonEnabledState() {
            boolean enable = StringUtils.isNotNullOrEmpty(firstName.get()) && StringUtils.isNotNullOrEmpty(lastName.get());
            helloButtonEnabled.set(enable);
        }

        public void buttonClicked() {
            helloText.set(String.format("Hello %s %s !", firstName.get(), lastName.get()));
        }
    }
}
