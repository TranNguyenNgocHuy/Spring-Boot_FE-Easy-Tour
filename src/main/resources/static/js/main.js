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

function insertUseInfo() {
    // Get Info User from local storage
    const userInfoJson = localStorage.getItem("userInfo");

    if (userInfoJson) {
        try {
            const userInfoObject = JSON.parse(userInfoJson);

            const userName = userInfoObject.firstName + " " + userInfoObject.lastName;

            const headerUserName = document.querySelector(".header-user-name");

            if (headerUserName) {
                  headerUserName.textContent = userName;
                  console.log("user ok!")
            } else {
                  console.error("Element with class 'header-user-name' not found.");
                   }
        } catch (error) {
                     console.error("Error parsing user info:", error);
        }
    } else {
              console.error("User info not found in localStorage.");
    }

}

