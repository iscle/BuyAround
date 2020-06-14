package cat.buyaround.app.viewmodel;

import android.text.TextUtils;

import androidx.lifecycle.ViewModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import cat.buyaround.app.callback.RegisterCallback;
import cat.buyaround.app.model.User;
import cat.buyaround.app.network.BuyAroundRepository;

import javax.inject.Inject;

public class RegisterViewModel extends ViewModel {

    private BuyAroundRepository buyAroundRepository;

    @Inject
    public RegisterViewModel(BuyAroundRepository buyAroundRepository) {
        this.buyAroundRepository = buyAroundRepository;
    }

    public void register(String name, String email, String password, RegisterCallback registerCallback) {
        User user = new User(name, email, password);
        buyAroundRepository.register(user, registerCallback);
    }

    public boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    public boolean isValidPassword(String text) {
        return text.matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{8,}$");
    }

    public boolean isValidBirthday(String birthday) {
        int age = 0;
        SimpleDateFormat format = new SimpleDateFormat("dd / MM / yyyy");

        try {
            Calendar birthdayDate = Calendar.getInstance();
            birthdayDate.setTime(format.parse(birthday));

            Calendar currentDate = Calendar.getInstance();

            age = currentDate.get(Calendar.YEAR) - birthdayDate.get(Calendar.YEAR);

            if (currentDate.get(Calendar.MONTH) < birthdayDate.get(Calendar.MONTH)) {
                age--;
            } else if (currentDate.get(Calendar.MONTH) == birthdayDate.get(Calendar.MONTH)
                    && currentDate.get(Calendar.DAY_OF_MONTH) < birthdayDate.get(Calendar.DAY_OF_MONTH)) {
                age--;
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return age >= 18;
    }
}
