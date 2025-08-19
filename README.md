✅ Chrome sends Accept: text/html (and others like */*).
✅ Jersey picks the first matching type (HTML) and tries to serialize your object to HTML.
❌ No HTML serializer exists, so it fails → 500 error.
✅ @Produces("application/json") forces Jersey to use JSON instead.
✅ Chrome accepts JSON too, so it works.
