$('#searchButton').click(function () {
  console.log('hi')
  var openWeatherAPIKey = '33132812ca0f9e4b588b781107d511fe'
  var url = 'http://api.openweathermap.org/data/2.5/weather?'
  var location = $('#location').val()
  if (location && location != '') {
    location = location.trim()
    var qwery = url + 'q=' + location + '&units=metric' + '&lang=ua' + '&appid=' + openWeatherAPIKey;
    console.log(qwery)
    var result = $.getJSON(qwery)
    result.success(function () {
      displayWeather(result.responseText)
    })

    result.fail(function () {})
  } else {
    console.log('fail  ')
  }
})



function displayWeather (result) {
  var wData = JSON.parse(result)
  $('.data').remove()
  $('.content').append("<div class='data'></div>")
  $('.data').append("<div class='prop'><span class='big'>" + wData.main.temp + '&deg</span></div>')
    .append("<div class='prop'>" + "<img src='http://openweathermap.org/img/w/" + wData.weather[0].icon + ".png' title='image decription'>")
  $('.data').append("<div  id='box'></div>")
  $('.data').append("<div class='location'><b>" + wData.name + '(' + wData.sys.country + ')</b></div>')
  $('#box').append("<div class='metrix'>" + '<b>Wind:</b>' + wData.wind.speed + ' m/s ' + wData.wind.deg + '&deg</div')
  $('#box').append("<div class='metrix'>" + '<b>Pressure:</b>' + wData.main.pressure + ' mm</div>')
}

function initMap () {
  var uluru = {lat: -25.363, lng: 131.044}
  var map = new google.maps.Map(document.getElementById('map'), {
    zoom: 4,
    center: uluru
  })
  var myLatlng = new google.maps.LatLng(-25.363882, 131.044922)
  var mapOptions = {
    zoom: 4,
    center: myLatlng
  }
  var map = new google.maps.Map(document.getElementById('map'), mapOptions)

  // Place a draggable marker on the map
  var marker = new google.maps.Marker({
    position: myLatlng,
    map: map,
    draggable: true,
    title: 'Drag me!',
    animation: google.maps.Animation.DROP,
        icon: 'https://cdn1.iconfinder.com/data/icons/Map-Markers-Icons-Demo-PNG/48/Map-Marker-Marker-Outside-Pink.png',
   
  })

    function initialize() {
      var my_position = new google.maps.LatLng(50.5, 4.5);
      var map = new google.maps.Map(document.getElementById('map'), {
        center: my_position,
        zoom: 15
      });
      var marker = new google.maps.Marker({
        position: my_position,
        map: map
      });
      // double click event
      google.maps.event.addListener(map, 'dblclick', function(e) {
        var positionDoubleclick = e.latLng;
        marker.setPosition(positionDoubleclick);
             var openWeatherAPIKey = '33132812ca0f9e4b588b781107d511fe';
    var url = 'http://api.openweathermap.org/data/2.5/weather?';
    var qwery = url + 'lat=' + marker.getPosition().lat() +'&lon=' +marker.getPosition().lng()+ '&units=metric' + '&lang=ua' + '&appid=' + openWeatherAPIKey;
    var result = $.getJSON(qwery)
    result.success(function () {
      displayWeather(result.responseText)
    })
        // if you don't do this, the map will zoom in
       e.stopPropagation();
        
      });
    }

    google.maps.event.addDomListener(window, 'load', initialize);
}
