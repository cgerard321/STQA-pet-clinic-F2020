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
      console.log(pets)
        // if(pets.length === 2){
        //     $("#HOF3Name").hide();
        //     $("#HOF3Img").hide();
        // }
        // else if(pets.length === 1){
        //     $("#HOF2Name").hide();
        //     $("#HOF2Img").hide();
        //     $("#HOF3Name").hide();
        //     $("#HOF3Img").hide();
        // }
        for (var x = 0; x < 3; x++){
                if (pets[x].totalRating == 0){
                    $("#HOF" + (x+1) + "Name").text(pets[x].name + " [0]");
                }
                else{
                    $("#HOF" + (x+1) + "Name").text(pets[x].name + " [" + parseFloat((pets[x].totalRating / pets[x].timesRated).toFixed(2)) + "]");
                }
                $("#Name" + (x+1)).text(pets[x].name);
                // $("#owner" + (x+1)).text("Owner: " + pets[x].ownerElement);
                $("#timesRated" + (x+1)).text("Times Rated: " + pets[x].timesRated);
                $("#HOF" + (x+1) + "Img").attr("src", pets[x].imageURL);
            }
        }


});



