let input_dom = document.querySelector("#insert-url");
input_dom.addEventListener("keyup", (e)=>{
    let youtubeUrl = input_dom.value;
    parseYoutube(youtubeUrl);
});


async function parseYoutube(youtubeUrl){
    let response = await fetch(`/api/oembeded?youtubeUrl=${youtubeUrl}`);
    let responseParse = await response.json();
    insertVideo(responseParse);
}

function insertVideo(value){
    let myVideo_dom = document.querySelector("#my-video");
    myVideo_dom.innerHTML = value.html;
}