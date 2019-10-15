const express = require('express');
const fs = require('fs');

const app = express();
app.use('/', express.static('./public'));
app.use(express.json());

function readdir(dirUrl) {
    return new Promise((resolve, reject) => {
        fs.readdir(dirUrl, (err, files) => {
            if (err)
                reject(err);
            else
                resolve(files);
        });
    });
}

app.get('/api/getAllVideoURL', async (req, res) => {
    files = await readdir('./public');
    
    files = files.filter(file => {
        if (file.endsWith('mp4'))
            return true;
        return false;
    });
    let urls = files.map(file => {
        return 'http://imust-s.com/' + file;
    });

    console.log(files);

    let data = [];

    console.log(files.length);
    for (let i = 0; i < files.length; i++) {
        let category = '围棋';
        if (files[i].includes('数独'))
            category = '数独';
        else if (files[i].includes('魔方'))
            category = '魔方'
        let tmp = {
            title: files[i].substr(0, files[i].length - 4),
            url: urls[i],
            category: category
        };
        data.push(tmp);
    }
    console.log(data);
    res.send(data);
});

app.listen(80);