window.onload=function(){

    let average = 0;
    let allarray = document.getElementsByTagName('input');
    //console.log(allarray);


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
                arrneed.push({ 'name': nname, 'value': nvalue * allarray[j].getAttribute('data-weight')});

            }
            //console.log(sum);
            //console.log(arrneed);

            max = arrneed[0]['value'];
            maxIndex = 0;
            for (let k = 0; k < arrneed.length; k++) {
                if (arrneed[k]['value'] > max) {
                    max = arrneed[k]['value'];
                    maxIndex = k;
                }
            }


            average = sum / householdtip;
            //console.log(householdtip);

            document.getElementById('total_per_year').innerHTML = average.toFixed(2);
            //console.log(average);

            //找到最大值，并根据name找到输出的p标签句子
            let tipmax = arrneed[maxIndex]['name'];
            var tipall=document.querySelectorAll('.tip');

            for(let i = 0;i<tipall.length;i++){
                tipall[i].style.display='none';
            }
            console.log(tipall);
            document.getElementById('tip_'+tipmax).style.display='block';
            console.log(document.getElementById('tip_'+tipmax));



            /*
            var oldmaxIndex = 100;
            function hide(){
                for(let i=0;i<tipall.length;i++){
                    tipall[i] = document.getElementById('tip_'+tipmax).style.display='none';
                }
            }

            if(i==maxIndex){
                tipall[maxIndex].style.display='block';
                oldmaxIndex = maxIndex;
                console.log(oldmaxIndex);
                console.log(maxIndex);
            }


            if(oldmaxIndex != maxIndex){
                console.log(oldmaxIndex);
                console.log(maxIndex);
                hide();
            }



             */







            document.getElementById('biggest-category').innerHTML = tipmax;

/*
            for(let i = 0;i<tipall.length;i++){
                tipall[i] = document.getElementById('tip_'+arrneed[maxIndex]['name']).style.display='none';
                console.log(tipall[i]);
            }

                tipall[maxIndex].style.display='block';
            //可以找到要输出的P标签
                console.log(tipall[maxIndex]);

            document.getElementById('biggest-category').innerHTML = arrneed[maxIndex]['name'];


 */
            //document.getElementById('tip_'+arrneed[maxIndex]['name']).style.display='block';
            //console.log(document.getElementById('tip_'+arrneed[maxIndex]['name']));





            //document.getElementById('biggest-category').innerHTML = arrneed[maxIndex]['name'];

            //监听数值改变，从而改变tip值
            /*
            var userinput = document.getElementById('biggest-category');
            userinput.addEventListener('change',function(){
                var tipall=document.querySelectorAll('.tip');
                for(let i = 0;i<tipall.length;i++){
                    tipall[i] = document.getElementById('tip_'+arrneed[maxIndex]['name']).style.display='block';
                    //console.log(tipall[i]);
                }
                document.getElementById('biggest-category').innerHTML = arrneed[maxIndex]['name'];
            })

             */



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
    document.getElementById('reset').addEventListener('click',function (){
        location.reload();
    });

}