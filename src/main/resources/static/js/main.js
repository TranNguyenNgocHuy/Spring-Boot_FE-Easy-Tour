console.log("main.js run");


// Save User Information to Local Storage
function saveUserInfoToLocalStorage(inputId) {
   try {
        const userInfoValue = document.getElementById(inputId).value;

            if (userInfoValue && userInfoValue.trim() !== "") {
                try {
                    const userInfoObject = JSON.parse(userInfoValue);

                    localStorage.setItem("userInfo", JSON.stringify(userInfoObject));

                    console.log("Success save userInfo to localStorage!")
                } catch (err) {
                    console.log("Error when save userInfo: ", err);
                }
            }
   } catch(err) {
        return;
   }
}

function controlShowUseInfo() {
    // Get Info User from local storage
    const userInfoJson = localStorage.getItem("userInfo");

    if (userInfoJson) {
        try {
            const userInfoObject = JSON.parse(userInfoJson);

            // get local html tag
            const headerUserName = document.getElementById("header-user-name");
            const dashboardUserName = document.getElementById("dashboard-username");
            const headerImgAvatar = document.getElementById("header-user-image");
            const profileImgAvatar = document.getElementById("profile-output-image");


            // set user Name
            const userName = userInfoObject.firstName + " " + userInfoObject.lastName;

            // set avatar image
            let urlImgAvatar = "";
            if (("avatarImg" in userInfoObject) && userInfoObject.avatarImg !== "") {
                urlImgAvatar = userInfoObject.avatarImg
                if ( urlImgAvatar.includes("\\img\\avatar")) {
                   urlImgAvatar = urlImgAvatar.substring(urlImgAvatar.indexOf("\\img\\avatar"));
                }
                console.log(urlImgAvatar)
            }

            // insert data
            if(headerUserName) {
                headerUserName.textContent = userName;
            }

            if(dashboardUserName) {
                dashboardUserName.textContent = userName;
            }

            if(headerImgAvatar && userInfoObject.avatarImg) {
                headerImgAvatar.src = urlImgAvatar;
            }

            if (profileImgAvatar && userInfoObject.avatarImg) {
                profileImgAvatar.src = urlImgAvatar;
            }
        } catch (error) {
                     console.error("Error parsing user info:", error);
        }
    } else {
              console.error("User info not found in localStorage.");
    }

}

function createLinkProfileUser() {
     document.addEventListener("DOMContentLoaded", function() {
        // Get Info User from local storage
        const user = JSON.parse(localStorage.getItem("userInfo"));

        if (user && user.uuid) {
           // Tạo URL với UUID
           const url = "/admin/v1/profile/update?uuid=" + user.uuid;

           // Cập nhật thuộc tính href của thẻ a
           const profileLink = document.getElementById("profile-update-link");
              if (profileLink) {
                   profileLink.href = url;
              }
        }
     });
}

function uploadImage(idUploadBtn, idInput, idOutput) {
    const uploadButton = document.getElementById(idUploadBtn);
    const inputImage = document.getElementById(idInput);
    const outputImage = document.getElementById(idOutput);

    uploadButton.addEventListener('click', function(event) {
         event.preventDefault();
         inputImage.click();
    });

    inputImage.addEventListener('change', function() {
    const file = this.files[0];
    if (file) {
        const reader = new FileReader();
        reader.onload = function(event) {
            outputImage.src = event.target.result;
        };
        reader.readAsDataURL(file);
        }
    })
}

