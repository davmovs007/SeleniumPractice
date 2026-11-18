# 🧪 QA Test Cases — Configure Studio Pro & Full Checkout Flow

**Target Page:** Configure Studio Pro (`Atelier Retail Storefront`)  
**Base Price:** `$1,599`  
**Total Test Cases:** `39`

---

## 📊 Summary & Priority Legend

| Priority | Meaning | Count |
| :--- | :--- | :---: |
| `P0` | Critical — Core pricing, selection, and checkout blockers | 11 |
| `P1` | High — Key interactive UI states and validation rules | 20 |
| `P2` | Medium — Edge cases, responsiveness, and accessibility | 7 |
| `P3` | Low — Minor UI/copy/environment checks | 1 |

---

## Part 1: Original 18 Test Cases (Exact Match)

### 1. Navigation & Header
- [ ] **TC-01** `[P1]` **Top Navigation Links**
  - **Steps:** Click each top navigation link (`Atelier` logo, `Studio Pro`, `Accessories`, `Express Pay`, `Checkout`, `Explore` dropdown).
  - **Expected:** Each link navigates to its corresponding route; `Explore` opens a dropdown menu with valid sub-items.

- [ ] **TC-02** `[P1]` **Bag Counter Initial State**
  - **Steps:** Observe the `Bag (0)` indicator on initial page load.
  - **Expected:** Displays `Bag (0)` when no items have been added to the cart.

- [ ] **TC-03** `[P0]` **Reset Button Functionality**
  - **Steps:** Change Finish, Memory, and Pro Apps selections, then click `Reset` in the top-right header.
  - **Expected:** All configurations revert to default (`Silver`, `16GB Unified Memory`, `0 / 3 selected` for Pro Apps), and total price resets to `$1,599`.

- [ ] **TC-04** `[P3]` **Promo Banner Environment Leak Check**
  - **Steps:** Inspect the sub-header banner (`Save up to $650 with Atelier Trade In when you configure a new Studio Pro. http://192.168.10.35:5050`).
  - **Expected:** Verify that the raw local IP (`http://192.168.10.35:5050`) is removed or replaced with a proper clickable link/modal before production release.

---

### 2. Product Preview & Interactive Card (Left Column)
- [ ] **TC-05** `[P1]` **Default Product State**
  - **Steps:** Load the page and inspect the left product preview card.
  - **Expected:** Displays the `STUDIO PRO` monitor graphic in Silver, caption `Silver Finish · 16GB Unified Memory`, and the `Hover for Atelier Trade In estimate →` CTA button.

- [ ] **TC-06** `[P1]` **Dynamic Preview Updates**
  - **Steps:** Select `Space Black` or `Starlight`, and switch memory to `32GB` or `64GB`.
  - **Expected:** The monitor bezel/stand color updates to match the selected finish, and the summary label below updates dynamically (e.g., `Space Black Finish · 32GB Unified Memory`).

- [ ] **TC-07** `[P2]` **Trade-In Estimate Hover/Click Interaction**
  - **Steps:** Hover over (and click) the `Hover for Atelier Trade In estimate →` button.
  - **Expected:** A tooltip, popover, or modal appears displaying trade-in estimate details without breaking the layout.

---

### 3. Product Configuration & Pricing Logic (Right Column)
- [ ] **TC-08** `[P0]` **Finish Radio Selection**
  - **Steps:** Click `Silver`, `Space Black`, and `Starlight` one by one.
  - **Expected:** Only one finish can be selected at a time; active radio button and card border highlight properly; base price (`$1,599`) remains unchanged.

- [ ] **TC-09** `[P0]` **Memory Upgrade Pricing (`32GB`)**
  - **Steps:** Select `32GB Unified Memory (+$400)`.
  - **Expected:** Radio button selects `32GB`, deselects `16GB`, and top-right total price updates from `$1,599` to `$1,999`.

- [ ] **TC-10** `[P0]` **Memory Upgrade Pricing (`64GB`)**
  - **Steps:** Select `64GB Unified Memory (+$800)`.
  - **Expected:** Radio button selects `64GB`, deselects the previous option, and top-right total price updates from `$1,599` to `$2,399`.

- [ ] **TC-11** `[P0]` **Pro Apps Individual Selection**
  - **Steps:** Check `Logic Pro`, then `Final Cut`, then `Motion` individually.
  - **Expected:** Counter updates sequentially (`1 / 3 selected` → `2 / 3 selected` → `3 / 3 selected`). `Select All Pro Studio Apps` automatically checks when all 3 are selected, and total price updates accordingly (if priced).

- [ ] **TC-12** `[P1]` **Pro Apps "Select All" Toggle**
  - **Steps:** Click `Select All Pro Studio Apps` when `0 / 3` are selected, then click it again to uncheck.
  - **Expected:** Checking selects all three apps (`Logic Pro`, `Final Cut`, `Motion`) and sets counter to `3 / 3 selected`. Unchecking deselects all three and resets counter to `0 / 3 selected`.

- [ ] **TC-13** `[P1]` **Pro Apps Partial Deselection**
  - **Steps:** With `Select All Pro Studio Apps` checked (`3 / 3`), uncheck `Motion`.
  - **Expected:** `Select All Pro Studio Apps` automatically unchecks, `Logic Pro` and `Final Cut` remain checked, and counter updates to `2 / 3 selected`.

---

### 4. Lower Page Controls
- [ ] **TC-14** `[P0]` **Order Updates Constraint (`Select Exactly 2`)**
  - **Steps:** Scroll down to `Select Exactly 2 Order Updates` (`0 / 2`). Attempt to select 1, 2, and then a 3rd option.
  - **Expected:** Counter increments (`1 / 2`, `2 / 2`). Selecting a 3rd option is blocked (remaining checkboxes disabled) or triggers a validation error requiring exact selection of 2 options before checkout.

- [ ] **TC-15** `[P1]` **Trade-In Serial Eligibility Verification**
  - **Steps:** Scroll down to `Trade-In Serial Eligibility` and click `Verify Trade-In` with empty, invalid, and valid serial numbers.
  - **Expected:** Empty/invalid serials display an inline error message; a valid serial validates eligibility and applies the corresponding trade-in credit.

---

### 5. UI, Responsiveness & Accessibility
- [ ] **TC-16** `[P2]` **Sticky Header / Price Visibility on Scroll**
  - **Steps:** Scroll down to the bottom of the configurator options.
  - **Expected:** Total price (`$1,599` / updated total) or product preview remains sticky/accessible so users can track price changes while configuring lower options.

- [ ] **TC-17** `[P2]` **Responsive Layout (Tablet & Mobile)**
  - **Steps:** Resize viewport to tablet (`768px`) and mobile (`375px`) widths.
  - **Expected:** The two-column layout stacks cleanly into a single column; Finish cards, Memory rows, and Pro Apps checkboxes do not overlap or truncate.

- [ ] **TC-18** `[P2]` **Keyboard Navigation & Focus States**
  - **Steps:** Navigate through Finish radios, Memory radios, and Pro Apps checkboxes using `Tab`, `Arrow` keys, and `Spacebar`.
  - **Expected:** Focus rings are clearly visible, arrow keys cycle through radio groups, and `Spacebar` toggles checkboxes.

---

## Part 2: Additional 21 Test Cases (Appended)

### 6. Studio Pro Configurator & Adding to Bag
- [ ] **TC-19** `[P0]` **Strict Notification Selection (`Select Exactly 2 Order Updates`)**
  - **Steps:** Select 1 checkbox (`SMS Text Alert`), then a 2nd (`Email Summary`), then attempt to select a 3rd (`Wallet Pass`). Also test attempting to proceed with only 1 checkbox selected.
  - **Expected:** Counter updates accurately (`0 / 2` → `1 / 2` → `2 / 2`). Selecting a 3rd option is blocked (remaining checkboxes become disabled) or triggers a validation error. Proceeding/adding to bag is blocked when fewer than 2 options are selected.

- [ ] **TC-20** `[P0]` **Mandatory Software License Terms (`I agree to the Atelier Software License Terms *`)**
  - **Steps:** Attempt to click `Add Studio Pro to Bag` while the mandatory agreement checkbox (`*`) is unchecked, then check it and retry.
  - **Expected:** Without the checkmark, the button is disabled or displays a validation error; once checked, the configuration is added to the bag and the header counter updates from `Bag (0)` to `Bag (1)`.

- [ ] **TC-21** `[P1]` **Free Engraving Input Field (`Free Engraving`, 24-character limit)**
  - **Steps:** Check the pre-filled value (`Complimentary Engraving`, counter `23 / 24`), clear the field, enter 24 characters, and attempt to enter a 25th character as well as special characters/emojis.
  - **Expected:** The character counter updates dynamically in real time, and input beyond 24 characters is strictly restricted.

- [ ] **TC-22** `[P1]` **Dependent Store Pickup Dropdowns (`Store Pickup`)**
  - **Steps:** Check the state of `-- Select Store --` before choosing a region, then select a region in `-- Select Region --`, select a store, and click `Check Store Stock`.
  - **Expected:** Until a region is selected, the store dropdown remains disabled. After selecting a region, the store list unlocks and populates stores for that region; `Check Store Stock` displays current item availability.

- [ ] **TC-23** `[P1]` **Trade-In Serial Eligibility Button (`Trade-In Serial Eligibility`)**
  - **Steps:** Click the `Verify Trade-In` button in the card beneath the product preview.
  - **Expected:** Opens a serial number input form/modal; a valid serial number confirms discount eligibility, while an invalid one displays a clear error message.

- [ ] **TC-24** `[P1]` **Header Reset Button (`Reset`)**
  - **Steps:** Modify configurations, add items to the bag, and click `Reset` in the top-right corner.
  - **Expected:** All fields, counters, and bag state (`Bag (0)`) reset to their initial default values.

---

### 7. Accessories Catalog (`Essential Studio Accessories.`)
- [ ] **TC-25** `[P1]` **Catalog Search (`Search accessories...`)**
  - **Steps:** Type `Keyboard` into the search bar, then enter a non-existent query (e.g., `MonitorStand`), then clear the field.
  - **Expected:** The table filters in real time, the bottom counter (`Showing 4 accessories`) updates to match the number of visible items, and an empty state placeholder is shown when there are no matches.

- [ ] **TC-26** `[P0]` **Adding a Custom Accessory (`Add Quote`) — Validation & Submission**
  - **Steps:**
    1. Enter a name shorter than 3 characters (e.g., `AB`) and price `$50` → click `Add Quote`.
    2. Enter a name with 3+ characters (`Custom Stand`) and a price outside the valid range (`$9` and `$2001`).
    3. Enter valid data (`Custom Stand`, price `150`) and click `Add Quote`.
  - **Expected:** When constraints (`min 3 chars`, price range `$10–$2000`) are violated, validation errors appear and the item is not added. With valid data, the custom item is added to the list/bag and the `Showing X accessories` counter updates.

- [ ] **TC-27** `[P0]` **Adding & Removing Accessories from Table (`Add to Bag` / `Remove`)**
  - **Steps:** Click `Add to Bag` next to `Studio Keyboard Pro` (`In Stock`) and `Braided USB-C Cable` (`Low Stock`), then click `Remove`.
  - **Expected:** Clicking `Add to Bag` adds the item to the bag, updating both the `Bag is empty.` badge in the top-right corner of the card and `Bag (0)` in the header. Clicking `Remove` removes the item from the bag and resets the counter.

---

### 8. Express Checkout & Saved Builds (`Express Checkout & Saved Builds.`)
- [ ] **TC-28** `[P1]` **Drag-and-Drop Bundle Builder (`Build Express Bundle`)**
  - **Steps:** Drag the `AirPods Max Case` card into the dashed `Drop Here for Express` dropzone. Also test dropping the card outside the dropzone.
  - **Expected:** Hovering over the dropzone highlights it; releasing inside adds the item to the express bundle and updates the `Ready for express interaction` status. Releasing outside snaps the card back to its original position.

- [ ] **TC-29** `[P1]` **Double-Click to Authorize Payment (`Double-Click to Pay`)**
  - **Steps:** Perform a single click on `Double-Click to Pay`, then perform a rapid double-click (`dblclick`).
  - **Expected:** A single click does not trigger payment (may show a prompt/hint); a double-click initiates express payment authorization and updates the card status.

- [ ] **TC-30** `[P1]` **Saved Build Context Menu (`Saved Build: Studio M4 (Right-Click)`)**
  - **Steps:** Right-click on the `Saved Build: Studio M4 (Right-Click)` button.
  - **Expected:** The default browser context menu is intercepted and a custom action menu for the saved build opens (e.g., load configuration, apply, or delete).

- [ ] **TC-31** `[P2]` **Serial Lookup with Keyboard Modifiers (`Hardware Serial Lookup`)**
  - **Steps:** Type a serial number into `Enter serial (Shift for uppercase)` while holding `Shift` and in lowercase.
  - **Expected:** The field properly handles keyboard modifiers, converts characters to uppercase as needed, and performs hardware lookup.

---

### 9. Checkout & Verification Form (`Checkout & Verification.`)
- [ ] **TC-32** `[P0]` **Form Text & Numeric Field Validation**
  - **Steps:** Test boundary values and formats across all form inputs:
    - `Recipient Full Name`: clear pre-filled `Walk-in Guest`, test empty state and special characters.
    - `Email Address`: test invalid formats (`alex@icloud`, `test.com`) and valid format (`alex@icloud.com`).
    - `Mobile Phone (10 Digits)`: enter 9 digits, 10 digits (`4085550192`), attempt an 11th digit or letters; verify the `0 / 10` counter.
    - `Order Reference (8 A-Z/0-9)`: enter 7 characters, 8 valid characters (`APL8042X`), lowercase letters, and special characters; verify the `0 / 8` counter.
    - `Buyer Age (Financing: 18 - 95)`: test boundary values `17` (invalid), `18`, `95` (valid), `96` (invalid).
    - `Order Quantity (1 - 25)`: test `0` (invalid), `1`, `25` (valid), `26` (invalid).
    - `Trade-In Parcel Weight kg (0.5 - 30.0)`: test `0.4` (invalid), `0.5`, `2.4`, `30.0` (valid), `30.1` (invalid).
    - `4-Digit Security PIN`: enter 3 digits, 4 digits, and letters.
  - **Expected:** Invalid inputs display clear inline error messages and the card status remains `Unverified`. When all fields are valid and `Verify Checkout Details` is clicked, the status changes to `Verified`.

- [ ] **TC-33** `[P1]` **Unlock & Apply Student Promo Code (`Education Discount Code`)**
  - **Steps:** Attempt to click into the locked `Click Unlock Education Code to apply` field, then click `Unlock Education Code`, complete verification, and enter a code in `Uppercase A-Z` format.
  - **Expected:** Before clicking `Unlock Education Code`, the input is disabled. Once unlocked, it accepts uppercase Latin letters `A-Z` and applies the discount.

- [ ] **TC-34** `[P1]` **Clear Form Details (`Clear Details`)**
  - **Steps:** Fill out all fields in the form and click `Clear Details`.
  - **Expected:** All entered values, validation errors, and character counters reset to their default states.

---

### 10. Final Authorization & Retail Support (`Final Authorization`)
- [ ] **TC-35** `[P0]` **Unlock Final Order Button (`Fraud Protection Check`)**
  - **Steps:** Check the state of `Place Final Order` before verification (status `Locked`), then click `Prepare Final Checkout` and wait for the security check to complete.
  - **Expected:** Until verification completes, `Place Final Order` remains disabled (`Locked`). After running `Prepare Final Checkout` (and completing any cooldown timer if applicable), the button unlocks and allows placing the order.

- [ ] **TC-36** `[P1]` **Electronic Invoice Generation (`Electronic Invoice`)**
  - **Steps:** Click `Generate Invoice PDF` while status shows `Invoice Status: Idle`.
  - **Expected:** Status transitions through generation to completion (`Generated` / `Ready`), allowing the user to download or preview the PDF invoice.

- [ ] **TC-37** `[P2]` **Retail Support Alert Buttons (`Retail Support Alerts`)**
  - **Steps:** Click `Store Pickup Alert`, `Cancel Express Hold`, and `Add Gift Note` one by one.
  - **Expected:** Replaces the `No support dialog triggered yet.` placeholder with the corresponding dialog or status notification for each action.

---

### 11. Tax Document Upload & Order File Downloads (`Receipts & Tax Documents`)
- [ ] **TC-38** `[P0]` **Tax-Exempt Certificate Upload & Verification (`Choose File` & `Verify Certificate`)**
  - **Steps:**
    1. Click `Verify Certificate` with no file attached (`No tax document attached.`).
    2. Upload an unsupported file format (e.g., `.pdf`, `.png`, `.exe`).
    3. Upload a valid format (`.txt`, `.csv`, or `.json`) larger than `512 KB`.
    4. Upload a valid `.json` file under `512 KB` (e.g., `100 KB`) and click `Verify Certificate`.
  - **Expected:** Missing files, unsupported formats, or oversized files (`> 512 KB`) trigger a validation error. A valid file replaces `No file chosen` / `No tax document attached.` and passes verification upon clicking `Verify Certificate`.

- [ ] **TC-39** `[P1]` **Download Order Files (`Download Order Files`)**
  - **Steps:** Click `Download Receipt (.txt)` and `Download Catalog (.csv)` in the section showing `Ready to download.`.
  - **Expected:** The browser downloads non-empty files with the proper extensions (`.txt` and `.csv`) containing up-to-date order/catalog data.
