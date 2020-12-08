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

// Async function that create option tag and appends them to the selection
async function initPetSelect() {
    // Hold the Result of the fetch
    const result = await fetch("/spring_framework_petclinic_war/pets/petTypes");

    //if fetch results is ok
    if (result.ok) {
        // convert result to json
        (await result.json())
            //convert the json into array and loop through it
            .map(i => {
                //define a constant name object store store a option tag
                const option = document.createElement("option");
                //set the value of the option tag to the name(aka type) received from the results
                option.value = i.name.toString();
                //set the text of the option tag to the name(aka type) received from the results
                option.textContent = i.name.toString();
                //return the element tag
                return option;
            })
            // for each tag append into the select tag
            .forEach(o => document.getElementById("petType").appendChild(o));
    }
}
initPetSelect();

// Function that returns a filter hall of fame.
$('#sortHall').click(   async function filterByPetType(){
    // store Result of fetch
    const petResult = await fetch("/spring_framework_petclinic_war/pets/getAllPetsInJson");
    //Gets the the value of the petType Select.
    let mySelect = document.getElementById("petType").value;
    //Declare amd empty array by the name filtered pets
    let filteredPets=[];
    //if petResult is ok continue
    if (petResult.ok) {

        //convert results to json
        const petsJson = await petResult.json();

        //if selection result is not equal to all
        if(mySelect != "all") {
            //loop through the petsJson
            //parse the the data
            //if pettype is equal to my Select add the pet object to filteredPets array.
            for(var i =0;i<petsJson.length;i++){
                const petAsObject = JSON.parse(petsJson[i]);
                if(mySelect == petAsObject.type) {

                    filteredPets.push(petAsObject);

                }
            }
        }
        //if selection result is equal to all
        else if(mySelect == "all") {
            for(var i =0;i<petsJson.length;i++){
                //loop through the petsJson
                //parse the the data
                //add the pet object to filteredPets array.
          const petAsObject = JSON.parse(petsJson[i]);
            filteredPets.push(petAsObject);
            }

      }
        //Hide Hall of fame elements if not enough pets are found
        for (var i = 1; i <= 3; i++) {
            //show all elements
            $("#HOF" + (i) + "Name").show();
            $("#HOF" + (i) + "Img").show();
            if (i > filteredPets.length) {
                $("#HOF" + (i) + "Name").hide();
                $("#HOF" + (i) + "Img").hide();
                $("#Name" + (i)).text("");
            }

        }
        // loop through an array and display approtiate pets information
        for (var x = 0; x <= 3; x++){
                //If total rating is 0, set pet rating on hall of fame to 0
                if (filteredPets[x].totalRating == 0){
                    $("#HOF" + (x+1) + "Name").text(filteredPets[x].name + " [0]");
                }
                else{
                //Else a divide total rating by times rated to get a pets rating and print nets to the name
                    $("#HOF" + (x+1) + "Name").text(filteredPets[x].name + " [" + parseFloat((filteredPets[x].totalRating / filteredPets[x].timesRated).toFixed(2)) + "]");
                }
                //sets the name of the pet to the name retrieved in the array
                $("#Name" + (x+1)).text(filteredPets[x].name);
            //sets the  of the pet to the name retrieved in the array
            $("#timesRated" + (x+1)).text("Times Rated: " + filteredPets[x].timesRated);
            //sets the image of the pet to the image retrieved in the array
            $("#HOF" + (x+1) + "Img").attr("src", filteredPets[x].imageURL);
            }
        }


});


