import React, {useState} from "react";
import axios from "axios";

export default function Register() {

    const [firstName, setFirstName]  = useState("defaultFirstName");
    const [lastName, setLastName]  = useState("defaultLastName");
    const [eMail, setEMail]  = useState("defaultEMail");
    const [password, setPassword]  = useState("defaultPassword");

    function handleSubmit() {
        axios({
            method: 'post',
            url: 'http://localhost:8080/api/v1/registration',
            data: {
                "firstName": firstName,
                "lastName": lastName,
                "email": eMail,
                "password": password,
            }
        });
    }

    return (
        <div className="Register">

          <div>Register</div>

          <div>
              <div className="input-group">
                  <span>First name</span>
                  <input type="text"
                         name="firstName"
                         value={firstName}
                         onChange={e => setFirstName(e.target.value)}
                  />
              </div>

              <div className="input-group">
                  <span>Last name</span>
                  <input type="text"
                         name="lastName"
                         value={lastName}
                         onChange={e => setLastName(e.target.value)}
                  />
              </div>

              <div className="input-group">
                  <span>E-mail</span>
                  <input type="text"
                         name="eMail"
                         value={eMail}
                         onChange={e => setEMail(e.target.value)}
                  />
              </div>

              <div className="input-group">
                  <span>Password</span>
                  <input type="password"
                         name="password"
                         value={password}
                         onChange={e => setPassword(e.target.value)}
                  />
              </div>

          </div>

          <input type="button"
                 value="Submit"
                 onClick={handleSubmit}/>
        </div>
    )
}