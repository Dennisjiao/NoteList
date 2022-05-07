window.onload=function(){

    //Find input tag and input to allarray[]
    let average = 0;
    let allarray = document.getElementsByTagName('input');

    for(let i=0;i < allarray.length;i++){
        allarray[i].onchange = function() {
            var sum = 0;
            let arrneed = [];
            let householdnum = document.getElementById('in_household');

            //push name & value to arrneed,after that just handle arrneed[]
            for (let j = 0; j < allarray.length; j++) {

                sum = sum + allarray[j].getAttribute('data-weight') * allarray[j].value;
                nname = allarray[j].getAttribute('name');
                nvalue = allarray[j].value;
                arrneed.push({ 'name': nname, 'value': nvalue * allarray[j].getAttribute('data-weight')});

            }

            num = householdnum.selectedIndex;
            householdtip = householdnum.options[num].value;

            //Find the maxvalue about (data-weight*value) and maxId
            maxvalue = arrneed[0]['value'];
            maxId = 0;
            for (let k = 0; k < arrneed.length; k++) {
                if (arrneed[k]['value'] > maxvalue) {
                    maxvalue = arrneed[k]['value'];
                    maxId = k;
                }
            }

            average = sum / householdtip;
            document.getElementById('total_per_year').innerHTML = average.toFixed(2);

            //Get maxvalue tip and Output the corresponding tip
            let tipmax = arrneed[maxId]['name'];
            var tipall=document.querySelectorAll('.tip');

            for(let i = 0;i<tipall.length;i++){

                tipall[i].style.display='none';

            }
            console.log(tipall);
            document.getElementById('tip_'+tipmax).style.display='block';
            document.getElementById('biggest-category').innerHTML = tipmax;

            //Listen the change of people and calculator the average
            var second = document.getElementById('in_household');
            second.addEventListener('change',function(){

                var inhvalue = document.getElementById('in_household').selectedIndex;
                var finvalue = inhvalue+1;
                average = sum / finvalue;
                document.getElementById('total_per_year').innerHTML = average.toFixed(2);

            })
        }
    }

    //Reset button
    document.getElementById('reset').addEventListener('click',function (){
        location.reload();
    });

}