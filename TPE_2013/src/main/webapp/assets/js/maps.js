var generateOptions = function(zoom, center) {
    return {
        zoom: zoom,
        center: center,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    };
}

var getMarker = function(lat, long, description) {
    var restaurantPosition = new google.maps.LatLng(lat, long);
    return new google.maps.Marker({
        position: restaurantPosition,
        title: description
    });
};

var generateMapFromAddress = function(el) {
	var geocoder = new google.maps.Geocoder();
	geocoder.geocode({'address': el.data('address')}, function(results, status) {
	  if (status == google.maps.GeocoderStatus.OK) {  
	      
	      var el = $("#map-canvas");
	      
	      var lat = results[0].geometry.location.jb;
	      var long = results[0].geometry.location.kb;
	      
	      var marker = getMarker(lat, long, el.data('description'));
	      var myOptions = generateOptions(15, new google.maps.LatLng(lat, long));
	      
       	  var map = new google.maps.Map(document.getElementById("map-canvas"), myOptions);
       	  
       	  marker.setMap(map);
	  }
	});
};

$(document).ready(function(){
	var el = $("#map-canvas");
	if (el.length > 0) {
		generateMapFromAddress(el);
	}
});

    
