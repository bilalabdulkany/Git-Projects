Problem 1:

Write a RESTful web service which talks to a 3rd party weather service and caches data for 2 hours.
It exposes APIs to get weather by city, country and by polar coordinates.


Write a Sprint Boot application with embedded tomcat to expose the APIs. The codebase should be a Maven project.
Provide the documentation for each web service with following information:

URL, Method, Parameters.


Integrate the following 3rd party weather service to fetch weather information:

URL:
api.openweathermap.org/data/2.5/weather?q={city name}&appkey=ffa6f13ea40a22452bba3be726315d3f

Params:
q= city name and country code divided by comma, use ISO 3166 country codes


URL:
api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&appkey=ffa6f13ea40a22452bba3be726315d3f

Params:

lat, lon coordinates of the location of your interest



Problem 2:
Given two sorted arrays which contain positive integers. Merge both and produce third array in sorted order.

Array 1: [1,3,5,7,8]
Array 2: [2,4,6,9,10]
Result: [1,2,3,4,5,6,7,8,9,10]


int[] merge(int []arr1, int []arr2) {

}
