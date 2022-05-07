window.onload=function(){


    let average = 0;
    let allarray = document.getElementsByTagName('input');

    for(let i=0;i < allarray.length;i++){
        allarray[i].onchange = function() {
            var sum = 0;
            let arrneed = [];
            let householdnum = document.getElementById('in_household');
            //console.log(householdnum);
            num = householdnum.selectedIndex;
            //console.log(num);
            householdtip = householdnum.options[num].value;
            //console.log(householdtip);

            for (let j = 0; j < allarray.length; j++) {
                sum = sum + allarray[j].getAttribute('data-weight') * allarray[j].value;
                nname = allarray[j].getAttribute('name');
                nvalue = allarray[j].value;
                arrneed.push({'id': j, 'name': nname, 'value': nvalue * allarray[j].getAttribute('data-weight')});
            }
            //console.log(sum);
            console.log(arrneed);

            max = arrneed[0]['value'];
            maxIndex = 0;
            for (let k = 0; k < arrneed.length; k++) {
                if (arrneed[k]['value'] > max) {
                    max = arrneed[k]['value'];
                    maxIndex = k;
                }
            }

            //权值相乘最大 id
            //console.log(arrneed[maxIndex]);

            //最多输入的数 value值
            //console.log(maxIndex);

            average = sum / householdtip;
            console.log(householdtip);
            //var aver = document.getElementById('in_household')


            document.getElementById('total_per_year').innerHTML = average.toFixed(2);
            console.log(average);
            /*
            householdtip.addEventListener('change',function (){
                document.getElementById('total_per_year').innerHTML = average.toFixed(2);
            })

             */

            //找到最大值，并根据name找到输出的p标签句子

            let tipmax = arrneed[maxIndex]['name'];
            //console.log(tipmax);

            //让tip_max中的语句block显示出来
            //var obj1 = document.getElementsByTagName('p')[5].innerText;
            //var obj2 = document.getElementsByTagName('p')[6].innerText;
            //var obj = document.getElementById('sec_tip');
            //console.log(obj);

            var tipall=document.querySelectorAll('.tip');
            //console.log(tipall);
            for(let i = 0;i<tipall.length;i++){
                tipall[i] = document.getElementById('tip_'+arrneed[maxIndex]['name']).style.display='block';
                //console.log(tipall[i]);
            }
            document.getElementById('biggest-category').innerHTML = arrneed[maxIndex]['name'];




            //document.getElementById('tip_'+arrneed[maxIndex]['name']).innerText.style.display='block';
            //输出建议
            /*
            if((document.getElementById('tip_'+tipmax).innerText) !='undefined'){
                //console.log(document.getElementById('tip_'+tipmax).innerText);
                (document.getElementById('tip_'+arrneed[maxIndex]['name']).innerText).style.display='block';
            }

             */
            var second = document.getElementById('in_household');
            second.addEventListener('change',function(){

                var inhvalue = document.getElementById('in_household').selectedIndex;
                var finvalue = inhvalue+1;
                average = sum / finvalue;
                document.getElementById('total_per_year').innerHTML = average.toFixed(2);

            })


        }

    }

    document.getElementById('reset').addEventListener('click',function (){
        location.reload();
    });

}