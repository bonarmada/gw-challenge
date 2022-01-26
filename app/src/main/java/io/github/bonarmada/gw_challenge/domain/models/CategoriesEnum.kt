package io.github.bonarmada.gw_challenge.domain.models

enum class CategoriesEnum(
    val stringValue: String,
    var isSelected: Boolean = false
) {
    SoftwareEngineer("Software Engineer"),
    UX("UX"),
    Design("Design"),
    IT("IT"),
    Accounting("Accounting"),
    CustomerServiceCareer("Customer Service Career"),
    DataScience("Data Science"),
    Editor("Editor"),
    Education("Education"),
    HR("HR"),
    Law("Law"),
    Marketing("Marketing"),
    Mechanic("Mechanic"),
    MentalHealth("Mental Health"),
    Nurses("Nurses"),
    OfficeAdministration("Office Administration"),
    Product("Product"),
    ProjectManagement("Project Management"),
    Recruiting("Recruiting"),
    Retail("Retail"),
    Sales("Sales"),
    Videography("Videography"),
    Writer("Writer")
}