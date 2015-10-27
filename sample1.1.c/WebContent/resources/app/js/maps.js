
var map;
var infowindow;
var mylist=[];
var k=0;
var click=0;

function initMap(lat,lng) {
  var e = document.getElementById("ddlViewBy");
var strUser = e.options[e.selectedIndex].value;
var str1 = e.options[e.selectedIndex].text;

  var pyrmont = new google.maps.LatLng(lat, lng);
//  alert(pyrmont);
 // alert(str1);

  map = new google.maps.Map(document.getElementById('map'), {
    center: pyrmont,
    zoom: 16
  });

  infowindow = new google.maps.InfoWindow();

  var service = new google.maps.places.PlacesService(map);
  service.nearbySearch({
    location: pyrmont,
    radius: 500,
    types: [str1]
  }, callback);
}

function callback(results, status) {
  if (status === google.maps.places.PlacesServiceStatus.OK) {
	  mylist=[];
    for (var i = 0; i < results.length; i++) {
    mylist[i]=i+1 + ":" +results[i].name + "<br>";
      createMarker(results[i]);
    }
  }
}

function createMarker(place) {
  var placeLoc = place.geometry.location;
  var marker = new google.maps.Marker({
    map: map,
    position: place.geometry.location
  });

  google.maps.event.addListener(marker, 'click', function() {
    infowindow.setContent(place.name);
    infowindow.open(map, this);
  });
}



function myFunction()
{
	click++;
	var div = document.getElementById("display");
    if (click%2!=0)
    	div.innerHTML = mylist;
    else
    	div.innerHTML = null;
	
	
//	for (i=0;i<mylist.length;i++)
//	{
//		
//	document.write.innerHTML=i+1 + "." + mylist[i] + "<br >";
//	}
		
	
}

  function getLatLng() {
               var address = document.getElementById("txtAddress").value;
               var geocoder = new google.maps.Geocoder();
               geocoder.geocode({ 'address': address }, function(results, status) {
                    if (status == google.maps.GeocoderStatus.OK) {
                         var longaddress = results[0].address_components[0].long_name;
                         var latitude = results[0].geometry.location.lat();
                         var longitude = results[0].geometry.location.lng();
                        
                         initMap(results[0].geometry.location.lat(), results[0].geometry.location.lng());
                    } else {
                         alert('Geocode error: ' + status);
                    }
               });
          }
