 1.Chrome sends Accept: text/html (and others like */*).
 
 2.Jersey picks the first matching type (HTML) and tries to serialize your object to HTML.
 
 3.No HTML serializer exists, so it fails → 500 error.
 
 4.@Produces("application/json") forces Jersey to use JSON instead.
 
 5.Chrome accepts JSON too, so it works.
