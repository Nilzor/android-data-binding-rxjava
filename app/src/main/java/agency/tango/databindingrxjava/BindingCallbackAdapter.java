package agency.tango.databindingrxjava;

import android.databinding.Observable;

public class BindingCallbackAdapter extends Observable.OnPropertyChangedCallback {
    private Callback mCallback;

    public BindingCallbackAdapter(BindingCallbackAdapter.Callback callback) {
        if (callback == null) throw new IllegalArgumentException("Callback cannot be null");
        mCallback = callback;
    }

    @Override
    public void onPropertyChanged(Observable sender, int propertyId) {
        mCallback.onChanged();
    }

    public interface Callback {
        void onChanged();
    }
}
