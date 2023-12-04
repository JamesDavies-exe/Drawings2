import { processFigure } from "./paint.js";
// Cogemos los valores de la imagen que queremos mostrar
let jsonInfo = document.getElementById("initialJson");
console.log("original: " + jsonInfo.value)
// Lo pasamos a un array
let initialFigures = eval(jsonInfo.value);
// Creamos otro array con los valores del anterior (datos originales)
let drawArray = initialFigures;

console.log(drawArray)

const canvas = document.getElementById("myCanvas");
const ctx = canvas.getContext("2d");

for (var i = 0; i < initialFigures.length; i++){
    // Cogemos cada valor de la figura
    console.log(initialFigures[i]);
    const x = initialFigures[i].coordX;
    const y = initialFigures[i].coordY;
    let shape = initialFigures[i].type;
    let color = initialFigures[i].color;
    let size = initialFigures[i].size;
    let fill = initialFigures[i].fill;

    // Pintamos la 
    processFigure(shape, x, y, color, size, fill);
    document.getElementById("jsonInfo").value = jsonInfo;
}