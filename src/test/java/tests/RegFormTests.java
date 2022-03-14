package tests;

import org.junit.jupiter.api.Test;
import pages.RegForm;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class RegFormTests extends TestBase {

    RegForm regForm = new RegForm();

    String userName = "Vovan",
            userSurnname = "deMort'",
            userEmail = "vmort@mailinator.com",
            userGender = "Male",
            userPhone = "9005553311",
            userBdayMonth = "September",
            userBdayYear = "1999",
            userSubject = "History",
            userHobby = "Reading",
            userPictureName = "cat_4_qaguru_11.jpeg",
            userCurrAddress = "Main street,90210",
            userState = "Uttar Pradesh",
            userCity = "Agra",
            userBdayDay = "30";

    @Test
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
                .uploadPicture(userPictureName)
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
                .checkResult("Picture", userPictureName)
                .checkResult("Address", userCurrAddress)
                .checkResult("State and City", userState + " " + userCity);
    }

    @Test
    void test05() {
        assertTrue(true);
    }
    }
