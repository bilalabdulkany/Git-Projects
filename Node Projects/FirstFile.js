let hello="This is hello world from variable";
global.console.log(hello);
//inbuilt variables
console.log(__dirname);
console.log(__filename);

//modules using the require keyword
const path =require("path");
console.log(`The file name is ${path.basename(__filename)}`);