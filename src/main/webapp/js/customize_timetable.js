console.log("tesxting");

// get Dom Element
const form = document.getElementById('form');
const overlay = document.getElementById('overlay');
const addButtons = document.querySelectorAll('.add-btn'); // 所有 "Add" 按钮
const closeButton = document.getElementById('close-btn');

// show form and overlay
addButtons.forEach(button => {
    button.addEventListener('click', () => {
        form.classList.add('active'); // 显示表单
        overlay.classList.add('active'); // 显示遮罩
    });
});

// hide form and overlay
closeButton.addEventListener('click', () => {
    form.classList.remove('active');
    overlay.classList.remove('active');
});

// close form when click overlay (outside the form)
overlay.addEventListener('click', () => {
    form.classList.remove('active');
    overlay.classList.remove('active');
});
