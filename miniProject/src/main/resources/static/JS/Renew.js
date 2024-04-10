
    // Send AJAX request to update the status of the request

        function approveRequest() {
            var requestId=[[${r.requestId}]];
            let sendData = "this is data";
            var csrfToken = document.querySelector("meta[name='_csrf']").getAttribute("content");
        console.log(requestId);
            fetch("/approverequest",{
                credentials:"include",
                method:"POST",
                headers:{ 'Content-Type': 'text/plain',
                    'X-CSRF-TOKEN': csrfToken},
                body: {requestId}
            }).then((response) => {
                response.text().then(data => {
                    console.log(data);
                });
            }).catch(err => {
                console.log(err);
            });

    // let options={
    //     // credentials: 'include',
    //     method: 'POST',
    //
    //     header: {"Content-Type": "application/json;charset=UTF-8" },
    //     body: JSON.stringify({title:"foo",body:"bar",userId:1}),
    // }
    //         // Fetch API to send a POST request
    //         fetch("/approverequest",options)
    //             .then(response => {
    //                 if (!response.ok) {
    //                     throw new Error('Network response was not ok');
    //                 }
    //                 // Assuming the response is text/plain
    //                 return response.text();
    //             })
    //             .then(data => {
    //                 // Update the status cell in the corresponding row
    //                 document.getElementById('status-' + requestId).textContent = 'approved';
    //             })
    //             .catch(error => {
    //                 console.error('There was a problem with the fetch operation:', error);
    //             });

}
