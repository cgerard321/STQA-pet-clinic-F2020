var petArray;

$("#hallOfFame").click(function(){

    // const json = '{"result":true, "count":42}';
    // var obj = JSON.parse(json);
    //
    // console.log(obj.count);
    // console.log(obj.result);


    $.ajax({
        type: "GET",
        url: "/spring_framework_petclinic_war/pets/getPets",
        asynch: 'false',
        success: function (data) {
            petArray = data;
            for (var i = 0; i < petArray.length; i++) {
                var pet = JSON.parse(petArray[i]);
                console.log(pet.name);
            }
        },
        error: function (response) {
            console.log("Go kill yourself");
            //console.log(response.textContent);
        }
    });

});
