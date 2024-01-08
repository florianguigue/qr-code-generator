# QR Code Generator

**WIP**


## Error correction level and maximum character capacities

There is four error correction level : L, M, Q and H.

It's possible to specify the error correction level. It's an indicator that will affect the QrCode in two ways:
* Allow the QRcode to be still readable even if a portion of the code is damaged
* Affect the maximum number of characters

### Correction capacities

| Error Correction Level | Error Correction Capability |
|------------------------|-----------------------------|
| L                      | Recovers 7% of data         |
| M                      | Recovers 15% of data        |
| Q                      | Recovers 25% of data        |
| H                      | Recovers 30% of data        |

### Character capacities

Depending on the error correction level, the character capacities is impacted. A higher error correction, the less
character capacity you will have. This capacity is also affected by the encoding mode.

| Encoding Mode | L    | M    | Q    | H    |
|---------------|------|------|------|------|
| Numeric       | 7089 | 5596 | 3993 | 3057 |
| Alphanumeric  | 4296 | 3391 | 2420 | 1852 |
| Byte          | 2953 | 2331 | 1663 | 1273 |
| Kanji         | 1817 | 1435 | 1024 | 784  |
