var petArray;


// Set the Hall of Fame
$.ajax({
    type: "GET",
    url: "/spring_framework_petclinic_war/pets/getAllPetsInJson",
    success: function (data) {
        petArray = data;
        for (var i = 1; i <= 3; i++){
            if (i > petArray.length){
                $("#HOF" + (i) + "Name").hide();
                $("#HOF" + (i) + "Img").hide();
                $("#Name" + (i)).text("");
            }
            else {
                var obj = JSON.parse(petArray[i-1]);
                if (parseInt(obj.totalRating) === 0){
                    $("#HOF" + (i) + "Name").text(obj.name + " [0]");
                }
                else{
                    $("#HOF" + (i) + "Name").text(obj.name + " [" + parseFloat((obj.totalRating / obj.timesRated).toFixed(2)) + "]");
                }
                $("#Name" + (i)).text(obj.name);
                $("#owner" + (i)).text("Owner: " + obj.ownerElement);
                $("#timesRated" + (i)).text("Times Rated: " + obj.timesRated);
                $("#HOF" + (i) + "Img").attr("src", obj.imageURL);
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
async function init() {
    const result = await fetch("/spring_framework_petclinic_war//pets/petTypes");

    if (result.ok) {
        (await result.json())
            .map(i => {
                const option = document.createElement("option");
                option.value = i.name.toString();
                option.textContent = i.name.toString();
                return option;
            })
            .forEach(o => document.getElementById("petType").appendChild(o));
    }
}
init();
