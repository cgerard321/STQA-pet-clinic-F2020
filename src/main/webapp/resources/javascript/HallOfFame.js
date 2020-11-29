var petArray;
var top3Highest;

// Get Top 3 Highest Ratings
$.ajax({
    type: "GET",
    url: "/spring_framework_petclinic_war/pets/getHighestRatings",
    success: function (data) {
        top3Highest = data;
    },
    error: function (response) {
        console.log(response.textContent);
    }
});

// Set the Hall of Fame
$.ajax({
    type: "GET",
    url: "/spring_framework_petclinic_war/pets/getPets",
    success: function (data) {
        petArray = data;
        for (var i = 0; i < 3; i++){
            if ((Object.is((top3Highest[i] - 1), NaN))){
                $("#HOF" + (i+1) + "Name").hide();
                $("#HOF" + (i+1) + "Img").hide();
            }
            else {
                var obj = JSON.parse(petArray[top3Highest[i] - 1]);
                if (obj.totalRating == 0){
                    $("#HOF" + (i+1) + "Name").text(obj.name + " [0]");
                }
                else{
                    $("#HOF" + (i+1) + "Name").text(obj.name + " [" + parseFloat((obj.totalRating / obj.timesRated).toFixed(2)) + "]");
                }
                $("#Name" + (i+1)).text(obj.name);
                $("#owner" + (i+1)).text("Owner: " + obj.ownerElement);
                $("#timesRated" + (i+1)).text("Times Rated: " + obj.timesRated);
                $("#HOF" + (i+1) + "Img").attr("src", obj.imageURL);
            }
        }
    },
    error: function (response) {
        console.log(response.textContent);
    }
});

// Show Hall of Fame only when ready to avoid empty Pet names/images
$(document).ready(function(){
    $("#hallOfFame").show();
});
