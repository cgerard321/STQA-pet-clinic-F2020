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

async function initPetSelect() {
    const result = await fetch("/spring_framework_petclinic_war/pets/petTypes");

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
initPetSelect();



$('#sortHall').click(async function filterByPetType(){
    const result = await fetch("/spring_framework_petclinic_war/pets/getPets");
    let mySelect = document.getElementById("petType").value;
    let pets=[];

    if (result.ok) {
      const test = await result.json();

      for(var i =1;i<test.length;i++){
          const test2 = JSON.parse(test[i]);
        if(test2.type == mySelect) {
            pets.push(test2);
        }

      }
        for (var x = 0; x < 3; x++){
            if (pets[x] - 1) {
                $("#HOF" + (x+1) + "Name").hide();
                $("#HOF" + (x+1) + "Img").hide();
            }
            // else {
            //     var obj = JSON.parse(petArray[top3Highest[i] - 1]);
            //     if (obj.totalRating == 0){
            //         $("#HOF" + (i+1) + "Name").text(obj.name + " [0]");
            //     }
            //     else{
            //         $("#HOF" + (i+1) + "Name").text(obj.name + " [" + parseFloat((obj.totalRating / obj.timesRated).toFixed(2)) + "]");
            //     }
            //     $("#Name" + (i+1)).text(obj.name);
            //     $("#owner" + (i+1)).text("Owner: " + obj.ownerElement);
            //     $("#timesRated" + (i+1)).text("Times Rated: " + obj.timesRated);
            //     $("#HOF" + (i+1) + "Img").attr("src", obj.imageURL);
            // }
        }
        console.log(pets)


    }
});



