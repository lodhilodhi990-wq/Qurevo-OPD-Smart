# Milestone 1 — Thermal Printer Compatibility Matrix

---

## Tested & Compatible Printer Models

| Printer Model | Transport | Paper Width | Character Width | Compatibility Status |
|---|---|---|---|---|
| POS-58 Series (Zjiang, Xprinter) | Bluetooth SPP / USB | 58mm | 32 Chars/Line | VERIFIED & COMPATIBLE |
| POS-80 Series (Xprinter XP-N160I) | Bluetooth SPP / USB | 80mm | 48 Chars/Line | VERIFIED & COMPATIBLE |
| Epson TM-T20II / TM-T82 | Windows Spooler / USB | 80mm | 48 Chars/Line | VERIFIED & COMPATIBLE |
| Generic ESC/POS SPP Printers | Bluetooth Classic SPP | 58mm / 80mm | Variable | VERIFIED & COMPATIBLE |

---

## Supported ESC/POS Command Sequences
- **Initialize Printer**: `ESC @` (`0x1B 0x40`)
- **Text Alignment**: `ESC a n` (`0x1B 0x61 n`)
- **Bold Mode**: `ESC E n` (`0x1B 0x45 n`)
- **Text Character Size**: `GS ! n` (`0x1D 0x21 n`)
- **Feed Lines**: `ESC d n` (`0x1B 0x64 n`)
- **Paper Cut**: `GS V 66 0` (`0x1D 0x56 0x42 0x00`)
