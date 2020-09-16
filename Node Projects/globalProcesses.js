const { argv } = require("process");

console.log(process.pid);
console.log(process.versions.node);

//array of arguments sent to console
console.log(argv);

//pass array of variables to arguments
console.log("_____Array inputs_______");
const [,,firstName,lastName]=process.argv;
console.log(`Array inputs are ${firstName} ${lastName}`);

//Grab functions
console.log("_____Function to get defined users_______");

const grab= flag=>{

    let indexAfterFlag=process.argv.indexOf(flag)+1;
    return process.argv[indexAfterFlag];

}
const greeting=grab("--greeting");
const user=grab("--user");

console.log(`${greeting} ${user}`);