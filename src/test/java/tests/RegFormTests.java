package tests;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.RegForm;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;


public class RegFormTests extends TestBase {

    RegForm regForm = new RegForm();

    String userName = System.getProperty("User_First_Name","Vovan"),
            userSurnname = System.getProperty("User_Last_Name","deMort'"),
            userEmail = "vmort@mailinator.com",
            userGender = "Male",
            userPhone = "9005553311",
            userBdayMonth = "September",
            userBdayYear = "1999",
            userSubject = "History",
            userHobby = "Reading",
           // userPictureName = "cat_4_qaguru_11.jpeg",
            userCurrAddress = "Main street,90210",
            userState = "Uttar Pradesh",
            userCity = "Agra",
            userBdayDay = "30";



    @Test
    @Owner("DmitriyTQC")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Подключаем Jenkins с Allure report")
    @DisplayName("Результат заполнения формы соответствует введенным значениям в форме")
    void successFillPracticeForm() {
            regForm.openPage()
                .setFirstName(userName)
                .setLastName(userSurnname)
                .setEmail(userEmail)
                .selectMaleGender()
                .setPhone(userPhone)
                .setBirthDate(userBdayDay, userBdayMonth, userBdayYear)
                .setSubject(userSubject)
                .selectHobby()
                //.uploadPicture(userPictureName)
                .setAddress(userCurrAddress)
                .selectState(userState)
                .selectCity(userCity)
                .clickSubmitBTN();


        regForm.checkResultHeader()
                .checkResult("Label", "Values")
                .checkResult("Student Name", userName + " " + userSurnname)
                .checkResult("Student Email", userEmail)
                .checkResult("Gender", userGender)
                .checkResult("Mobile", userPhone)
                .checkResult("Date of Birth", userBdayDay + " " + userBdayMonth + "," + userBdayYear)
                .checkResult("Subjects", userSubject)
                .checkResult("Hobbies", userHobby)
                //.checkResult("Picture", userPictureName)
                .checkResult("Address", userCurrAddress)
                .checkResult("State and City", userState + " " + userCity);
    }

    @Test
    @Owner("DmitriyTQC")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Тесты формы")
    @DisplayName("Проверка возможности создать карточку студента с минимальным набором данных")
    void successMinFillPracticeForm() {
        regForm.openPage()
                .setFirstName(userName)
                .setLastName(userSurnname)
                .selectMaleGender()
                .setPhone(userPhone)
                .clickSubmitBTN();

        String dateNow = regForm.dateNow();


        regForm.checkResultHeader()
                .checkResult("Label", "Values")
                .checkResult("Student Name", userName + " " + userSurnname)
                .checkEmptyResult("Student Email")
                .checkResult("Gender", userGender)
                .checkResult("Mobile", userPhone)
                .checkResult("Date of Birth", dateNow)
                .checkEmptyResult("Subjects")
                .checkEmptyResult("Hobbies")
                .checkEmptyResult("Address")
                .checkEmptyResult("State and City");
    }
}
