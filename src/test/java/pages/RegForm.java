package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RegForm {
    //components
    private CalendarComponent calendarComponent = new CalendarComponent();

    //locators
    SelenideElement mainHeader = $(".main-header"),
            titleOfForm = $(".practice-form-wrapper"),
            submitBTN = $("#submit"),
            firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            emailInput = $("#userEmail"),
            genderMale = $("[for=gender-radio-1]"),
            phoneNumberInput = $("#userNumber"),
            bdayField = $(".react-datepicker__input-container"),
            subjectInput = $("#subjectsInput"),
            hobbyReading = $("[for=hobbies-checkbox-2]"),
            uploadPictureField = $("#uploadPicture"),
            addressInput = $("#currentAddress"),
            stateSelection = $("#state"),
            citySelection = $("#city"),
            resultHeader = $("#example-modal-sizes-title-lg"),
            resultTable = $(".table-responsive");

    //actions
    public RegForm openPage() {
        open("/automation-practice-form");
        mainHeader.shouldHave(text("Practice Form"));
        titleOfForm.shouldHave(text("Student Registration Form"));
        submitBTN.shouldHave(text("Submit"));
        return this;
    }

    public RegForm setFirstName(String firstName) {
        firstNameInput.setValue(firstName);
        return this;
    }

    public RegForm setLastName(String lastName) {
        lastNameInput.setValue(lastName);
        return this;
    }

    public RegForm setEmail(String email) {
        emailInput.setValue(email);
        return this;
    }

    public RegForm selectMaleGender() {
        genderMale.click();
        return this;
    }

    public RegForm setPhone(String phone) {
        phoneNumberInput.setValue(phone);
        return this;
    }

    public RegForm setBirthDate(String day, String month, String year) {
        bdayField.click();
        calendarComponent.setDate(day, month, year);
        return this;
    }

    public RegForm setSubject(String subject) {
        subjectInput.setValue(subject).pressEnter();
        return this;
    }

    public RegForm selectHobby() {
        hobbyReading.click();
        return this;
    }

    public RegForm uploadPicture(String picture) {
        uploadPictureField.uploadFromClasspath("./img/" + picture);
        return this;
    }

    public RegForm setAddress(String address) {
        addressInput.setValue(address);
        return this;
    }

    public RegForm selectState(String state) {
        stateSelection.scrollIntoView(true);
        stateSelection.click();
        $(byText(state)).click();
        return this;
    }

    public RegForm selectCity(String city) {
        citySelection.click();
        $(byText(city)).click();
        return this;
    }

    public RegForm clickSubmitBTN() {
        submitBTN.click();
        return this;
    }

    public RegForm checkResultHeader() {
        resultHeader.shouldHave(text("Thanks for submitting the form"));
        return this;
    }

    public RegForm checkResult(String fieldName, String value) {
        resultTable.$(byText(fieldName))
                .parent().shouldHave(text(value));
        return this;
    }
}
