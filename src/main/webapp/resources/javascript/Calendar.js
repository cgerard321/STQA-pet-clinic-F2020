var appointments;
var days = $("tbody tr td span.date").toArray();
var currMonth = getMonthFromString($("#currentMonth").text()).toString();
var currYear = $("#currentYear").text();

function getMonthFromString(mon) {
    return new Date(Date.parse(mon + " 1, 2020")).getMonth() + 1
}

function dateStringToDateObject(date) {
    return {
        year: date.substr(0, 4),
        month: date.substr(5, 2),
        day: date.substr(8, 2),
    };
}

$.ajax({
    type: "GET",
    url: "/spring_framework_petclinic_war/appointments/getAllAppointments",
    success: function (data) {
        for (let i = 0; i < data.length; i++) {
            let obj = JSON.parse(data[i]);
            let date = dateStringToDateObject(obj.date);

            if (date.year === currYear && date.month === currMonth) {
                // Array is base 0 -> -1
                // Check if ul doesn't exist already
                $(days[date.day - 1]).find("ul").append(
                    "<li>" +
                    "<span class='event'><b>" + obj.petName + "</b> " + obj.description + "</span>" +
                    "</li>");
            }
        }
    },
    error: function (response) {
        console.log(response.textContent);
    }
});

$.ajax({
    type: "GET",
    url: "/spring_framework_petclinic_war/event/getAllEvents",
    success: function (data) {
        for (let i = 0; i < data.length; i++) {
            let obj = JSON.parse(data[i]);
            let date = dateStringToDateObject(obj.date);

            if (date.year === currYear && date.month === currMonth) {
                // Array is base 0 -> -1
                // Check if ul doesn't exist already
                $(days[date.day - 1]).find("ul").append(
                    "<li>" +
                    "<span class='event'><b>" + obj.description + "</b></span>" +
                    "<span class='time'>" + obj.time + "</span>" +
                    "</li>");
            }
        }
    },
    error: function (response) {
        console.log(response.textContent);
    }
});
