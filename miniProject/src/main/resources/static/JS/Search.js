const search=()=>{
    console.log("called search method in js...........");
    let query=$("#search-input").val();

    if(query=='')
    {

    }
    else
    {
            console.log(query);
            let url=`http://localhost:8080/search/${query}`;
            fetch(url).then((response)=>{
            return response.json();
            }).then((data)=> {
            console.log(data);
            let text = `<div class='list-group'>`;
            data.forEach((book) => {
                text += `<a href="#" onclick="logf('${book}')" class='list-group-item list-group-action'> ${book} </a><br>`
                });
            
            text +=` </div>`;
               $(".search-result").html(text);
                $(".search-result").show();
            });
            $(".search-result").show();
        }
    };
const logf = (book) => {
    console.log(book)
    if(book.length < 1)
    {

    }
    else
    {
        let url=`http://localhost:8080/search/result/${book}`;
        fetch(url).then((response)=>{
            return response.json();
        }).then((dataResult)=> {
            console.log(dataResult);
            let textResult = `<div class='list-group container-result'>`;
            dataResult.forEach((bookResult) => {

                if (bookResult.dueDate === null) {
                    textResult += `<div  class='fakir' onclick="logf(${JSON.stringify(book)})">Book Name : ${bookResult.book.bookName} 
                <br>Branch : ${bookResult.book.branch}<br>BookId : ${bookResult.book.bookId}<br>Publisher : ${bookResult.book.publisher}
                <br>Book Available
                </div><br>`;
                } else {

                textResult += `<div  class='fakir' onclick="logf(${JSON.stringify(book)})">Book Name : ${bookResult.book.bookName} 
                <br>Branch : ${bookResult.book.branch}<br>BookId : ${bookResult.book.bookId}<br>Publisher : ${bookResult.book.publisher}
                <br>Return Date: ${bookResult.dueDate}
                </div><br>`;
            }
            });

            textResult +=` </div>`;
            $(".search-result").html(textResult);
            $(".search-result").show();
        });
        }

    function logfuck(book) {
        console.log(book); // Or do whatever you need with the book object
    }

};