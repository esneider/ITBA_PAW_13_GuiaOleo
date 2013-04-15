 var getMarker = function(lat, long, description) {
    var restaurantPosition = new google.maps.LatLng(lat, long);
    return new google.maps.Marker({
        position: restaurantPosition,
        title: description
    });
};

 var generateOptions = function(zoom, center) {
    return {
        zoom: zoom,
        center: center,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    };
}

var generateMapFromAddress = function(address, description) {
	var geocoder = new google.maps.Geocoder();
	geocoder.geocode( {'address': address}, function(results, status) {
	  if (status == google.maps.GeocoderStatus.OK) {      
	      var marker = getMarker(results[0].geometry.location.latitude, results[0].geometry.location.longitude, description);
	      
	      var el = document.getElementById("map-canvas");
	      var myOptions = generateOptions(4, new google.maps.LatLng(0,0));
	      
       	  var map = new google.maps.Map(el, myOptions);
       	  
       	  marker.setMap(map);
       	  map.setCenter(marker.position);
	  }
	});
};
        
