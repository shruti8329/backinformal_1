function numberToWords(num) {
    if (num === 0) return 'zero';

    const BELOW_TWENTY = ['ZERO', 'ONE', 'TWO', 'THREE', 'FOUR', 'FIVE', 'SIX', 'SEVEN', 'EIGHT', 'NINE', 'TEN', 'ELEVEN', 'TWELVE', 'THIRTEEN', 'FOURTEEN', 'FIFTEEN', 'SIXTEEN', 'SEVENTEEN', 'EIGHTEEN', 'NINETEEN'];
    const TENS = ['', '', 'TWENTY', 'THIRTY', 'FORTY', 'FIFTY', 'SIXTY', 'SEVENTY', 'EIGHTY', 'NINETY'];
    const THOUSANDS = ['', 'THOUSAND', 'LAKH', 'CRORE'];

    function convertHundreds(number) {
        let result = '';
        if (number > 99) {
            result += BELOW_TWENTY[Math.floor(number / 100)] + ' HUNDRED ';
            number %= 100;
        }
        if (number > 19) {
            result += TENS[Math.floor(number / 10)] + ' ';
            number %= 10;
        }
        if (number > 0) {
            result += BELOW_TWENTY[number] + ' ';
        }
        return result.trim();
    }

    function convertNumber(number) {
        if (number === 0) return 'ZERO';

        let result = '';
        let i = 0;

        while (number > 0) {
            let chunk;
            if (i === 1) {
                chunk = number % 100;
                number = Math.floor(number / 100);
            } else {
                chunk = number % 1000;
                number = Math.floor(number / 1000);
            }
            if (chunk !== 0) {
                let chunkWord = convertHundreds(chunk);
                if (i === 1 && chunkWord === '') {
                    chunkWord = '';
                }
                result = chunkWord + ' ' + THOUSANDS[i] + ' ' + result;
            }
            i++;
        }
        return result.trim();
    }

    function convertDecimal(decimal) {
        if (decimal > 0) {
            return convertNumber(decimal) + ' PAISE';
        }
        return '';
    }

    let [integerPart, decimalPart] = num.toString().split('.').map(Number);
    let integerWords = convertNumber(integerPart);
    let decimalWords = decimalPart ? convertDecimal(decimalPart) : '';

    if (decimalWords) {
        return integerWords + ' AND ' + decimalWords;
    } else {
        return integerWords;
    }
}

function formatDate(date) {
    if (!(date instanceof Date)) {
        throw new Error('Input must be a Date object');
    }

    const MONTHS = [
        'January', 'February', 'March', 'April', 'May', 'June',
        'July', 'August', 'September', 'October', 'November', 'December'
    ];

    const day = date.getDate().toString().padStart(2, '0');
    const month = MONTHS[date.getMonth()];
    const year = date.getFullYear();

    return `${day}-${month}-${year}`;
}

 
// const date = new Date();
// console.log(formatDate(date));  

 

export  {formatDate,numberToWords} ;