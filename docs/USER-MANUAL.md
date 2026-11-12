# OPD Smart Token Platform — User Manual

## 1. Receptionist Guide

### Registering a New Patient & Issuing Token
1. Open the **Reception Application** or Web Portal.
2. Click **Register Patient**.
3. Enter Patient Name, MR Number, Age, Gender, and Phone Number.
4. Select Department and Doctor.
5. Click **Save & Generate Token**.
6. The system generates a unique daily token (e.g. `T-025`) and displays the print preview.
7. Click **Print Thermal Ticket**.

---

## 2. Doctor Guide

### Managing Patient Consultation Queue
1. Open the **Doctor Application**.
2. Sign in with doctor credentials.
3. Click **Open Consultation Console**.
4. To call the next patient, press **NEXT**.
5. To re-announce the current patient on the TV screen, press **RECALL**.
6. To temporarily hold a patient, press **HOLD**.
7. To skip a non-present patient, press **SKIP**.
8. Once consultation is finished, press **COMPLETE**.
9. In an emergency, press **CALL EMERGENCY PATIENT**.

---

## 3. Waiting Room TV Display Guide

1. Turn on the Android TV or open browser at `http://SERVER-IP:27800`.
2. Select the waiting area channel/department.
3. The display presents:
   - **NOW SERVING**: Large token number, Doctor name, Room number.
   - **NEXT TOKENS**: List of upcoming tokens.
   - **Voice Announcement**: Plays clear Urdu and English audio announcements automatically whenever a token is called.

---

## 4. SaaS Super Admin Guide

1. Navigate to `http://SERVER-IP:27800` and select **SaaS Super Admin**.
2. To add a new hospital, enter Hospital Name and City, then click **Register Hospital**.
3. Review manual payment records for JazzCash, Easypaisa, or Bank Transfers.
4. Approve payments to activate subscriptions.
