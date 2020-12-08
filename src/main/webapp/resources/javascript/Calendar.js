var appointments;
var days = $("tbody tr td span.date").toArray();
var currMonth = getMonthFromString($("#currentMonth").text()).toString();
var currYear = $("#currentYear").text();
// var currDay = $("td.current-day").text().trim();

$.ajax({
    type: "GET",
    url: "/spring_framework_petclinic_war/appointments/getAllAppointments",
    success: function (data) {
        appointments = data;
        for (var i = 0; i < appointments.length; i++) {
            var obj = JSON.parse(appointments[i]);
            var appDate = obj.date;
            var appYear = appDate.substr(0,4);
            var appMonth = appDate.substr(5,2);
            var appDay = appDate.substr(8,2);
            if (appYear === currYear){
                if (appMonth === currMonth) {
                    // Array is base 0 -> -1
                    // Check if ul doesn't exist already
                    if ($(days[appDay - 1]).has("ul").length === 0) {
                        $(days[appDay - 1]).append("<ul></ul>");
                    }
                    $(days[appDay - 1]).find("ul").append("<li><span class='event'><b>" + obj.petName + "</b> " + obj.description + "</span></li>");
                }
            }
        }
    },
    error: function (response) {
        console.log(response.textContent);
    }
});

function getMonthFromString(mon){
    return new Date(Date.parse(mon +" 1, 2020")).getMonth()+1
}
