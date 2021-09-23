$(document).ready(() => {
    $('#main').on('click', function(event) {
        $.ajax({
            success : () =>{
                redirect('/admin')
            }
        });
    });
});

$(document).ready(() => {
    $('#studentApproval').on('click', function(event) {
        $.ajax({
            success : () =>{
                redirect('/studentApproval')
            }
        });
    });
});

$(document).ready(() => {
    $('#roleAssign').on('click', function(event) {
        $.ajax({
            success : () =>{
                redirect('/roleAssign')
            }
        });
    });
});

let usersJson ;
$(document).ready(() => {
    //ajax post to get all the cities
    $.ajax({
        url: '/api/users', // contains all the cities
        type: 'GET',
        dataType : 'json',
        headers : {
            'Content-Type' : 'application/json; charset=utf-8'
        },
        success: (result) => {
            usersJson = result;
            $.each(result, function(index,value){
                if (value["approved"] === false){
                    var id = value.id;
                    $('#approval-list').append("<tr>"+"<td>"+value.id+"</td>"+"<td>"+value.firstName+"</td>"+"<td>"+value.lastName+"</td>"+
                        "<td>"+31+"</td>"+"<td>"+value.grade+"</td>"+"<td>"+value.section+"</td>"+"<td>"+value.phoneNumber+"</td>"+"<td>"+value.email+"</td>"+
                        '<td><button  onclick="SomeDeleteRowFunction(this)" id="'+id+"assign"+'">'+"Onay"+"</button></td>"+'<td><button  onclick="SomeDeleteRowFunction(this)" id="'+id+"delete"+'">'+"Sil"+"</button></td>"+"<tr>")
                    $('#'+id+"assign").on('click', function() {
                        $.ajax({
                            url: '/api/approveUser/'+id, // contains all the cities
                            type: 'GET',
                            dataType : 'json',
                            headers : {
                                'Content-Type' : 'application/json; charset=utf-8'
                            },
                        });
                    });

                    $('#'+id+"delete").on('click', function() {
                        $.ajax({
                            url: 'api/rejectUser/'+id,
                            type: 'GET',
                            dataType: 'json',
                            headers: {
                                'Content-Type': 'application/json; charset=utf-8'
                            }
                        })

                    });
                }
            });
        }, // end of ajax success
        error : function (result){
            console.log('city ajax GET failed! with result : ', result);
        }
    }); // end of ajax GET cities
});


function SomeDeleteRowFunction(o) {
    const p = o.parentNode.parentNode;
    p.parentNode.removeChild(p);
}

let usersJsonAssignment ;
$(document).ready(() => {
    //ajax post to get all the cities
    $.ajax({
        url: '/api/users', // contains all the cities
        type: 'GET',
        dataType : 'json',
        headers : {
            'Content-Type' : 'application/json; charset=utf-8'
        },
        success: (result) => {
            usersJsonAssignment = result;
            $.each(result, function(index,value){
                if (value["approved"]===true){
                    var id = value.id;
                    $('#assignment-list').append("<tr>"+"<td>"+value.id+"</td>"+"<td>"+value.firstName+"</td>"+"<td>"+value.lastName+"</td>"+
                        "<td>"+value.phoneNumber+"</td>"+"<td>"+value.email+"</td>"+
                        '<td><button id="'+id+"STUDENT"+'">'+"Öğrenci"+"</button></td>"+'<td><button id="'+id+"TEACHER"+'">'+"Öğretmen"+"</button>" +
                        "</td>"+'<td><button  id="'+id+"ADMIN"+'">'+"Admin"+"</button></td>"+"</tr>")

                    if (value["role"]==="STUDENT")
                        $('#'+id+"STUDENT").attr('disabled',true)
                    else if (value["role"]==="TEACHER")
                        $('#'+id+"TEACHER").attr('disabled',true)
                    else if (value["role"]==="ADMIN")
                        $('#'+id+"ADMIN").attr('disabled',true)

                    $('#'+id+"STUDENT").on('click', function() {
                        $.ajax({
                            url: '/api/assignRole/'+id+ "/"+0,
                            type: 'GET',
                            dataType : 'json',
                            headers : {
                                'Content-Type' : 'application/json; charset=utf-8'
                            },
                            success(){
                                $('#'+id+"STUDENT").attr('disabled',true)
                                $('#'+id+"TEACHER").attr('disabled',false)
                                $('#'+id+"ADMIN").attr('disabled',false)
                            }
                        });
                    });
                    $('#'+id+"TEACHER").on('click', function() {
                        $.ajax({
                            url: '/api/assignRole/'+id + "/"+1,
                            type: 'GET',
                            dataType : 'json',
                            headers : {
                                'Content-Type' : 'application/json; charset=utf-8'
                            },
                            success(){
                                $('#'+id+"STUDENT").attr('disabled',false)
                                $('#'+id+"TEACHER").attr('disabled',true)
                                $('#'+id+"ADMIN").attr('disabled',false)
                            }
                        });
                    });
                    $('#'+id+"ADMIN").on('click', function() {
                        $.ajax({
                            url: '/api/assignRole/'+id+ "/"+2,
                            type: 'GET',
                            dataType : 'json',
                            headers : {
                                'Content-Type' : 'application/json; charset=utf-8'
                            },
                            success(){
                                $('#'+id+"STUDENT").attr('disabled',false)
                                $('#'+id+"TEACHER").attr('disabled',false)
                                $('#'+id+"ADMIN").attr('disabled',true)
                            }
                        });
                    });
                }

            });
        }, // end of ajax success
        error : function (result){
           // console.log('city ajax GET failed! with result : ', result);
        }
    });
});

//This function search users by using their name in assignment-list table
function searchFunction() {
    var input, filter, table, tr, td, i, txtValue;
    input = document.getElementById("searchInput");
    filter = input.value.toUpperCase();
    table = document.getElementById("assignment-list");
    tr = table.getElementsByTagName("tr");
    for (i = 0; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td")[1];
        if (td) {
            txtValue = td.textContent || td.innerText;
            if (txtValue.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }
}