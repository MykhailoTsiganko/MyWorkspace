
$('#searchButton').click(function(){
    console.log("hi")
    var openWeatherAPIKey = "33132812ca0f9e4b588b781107d511fe";    
    var url = "http://api.openweathermap.org/data/2.5/weather?";
    var location = $("#location").val(); 
    if(location&&location!='') {
        location = location.trim();
        var qwery = url + "q=" + location + "&units=metric"+"&lang=ua" +"&appid="+openWeatherAPIKey;
        console.log(qwery);
        var result = $.getJSON(qwery);
        result.success(function(){
            displayWeather(result.responseText);
        });

        result.fail(function(){
        })

        
    } else {
        console.log("fail  ")
    }

    
})

function displayWeather(result) {
    var wData = JSON.parse(result);
    $('.data').remove();
    $('.content').append("<div class='data'></div>");
    $('.data').append("<div class='prop'><span class='big'>" + wData.main.temp   +  "&deg</span></div>")
    .append("<div class='prop'>" + "<img src='http://openweathermap.org/img/w/" + wData.weather[0].icon + ".png' title='image decription'>");
    $(".data").append("<div  id='box'></div>")
    $('.data').append("<div class='prop2'><b>" + wData.name + "(" + wData.sys.country + ")</b></div>");
    $("#box").append("<div class='metrix'>" + "Wind:" + wData.wind.speed + " m/s " + wData.wind.deg + "&deg</div");
    $("#box").append("<div class='metrix'>" + "Pressure:" + wData.main.pressure + " mm</div>");
}

 function initMap() {
        var uluru = {lat: -25.363, lng: 131.044};
        var map = new google.maps.Map(document.getElementById('map'), {
          zoom: 4,
          center: uluru
        });
        var marker = new google.maps.Marker({
          position: uluru,
          map: map
        });
      } 