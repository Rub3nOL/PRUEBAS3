const params = new URLSearchParams(location.search);

if (params.has('nombre')) {
  sessionStorage.setItem('inv_nombre', params.get('nombre'));
  sessionStorage.setItem('inv_rol',    params.get('rol') || '');
  history.replaceState(null, '', location.pathname);
}

const nombre = sessionStorage.getItem('inv_nombre');
const rol    = sessionStorage.getItem('inv_rol');

if (!nombre) {
  location.replace('/');
}

document.addEventListener('DOMContentLoaded', function () {
  const nameSpan = document.createElement('span');
  nameSpan.className = 'user-badge-name';
  nameSpan.textContent = nombre;

  const rolSpan = document.createElement('span');
  rolSpan.className = 'user-badge-rol';
  rolSpan.dataset.rol = rol;
  rolSpan.textContent = rol;

  const badge = document.createElement('div');
  badge.className = 'user-badge';
  badge.appendChild(nameSpan);
  badge.appendChild(rolSpan);

  const header = document.querySelector('.header');
  const nav    = document.querySelector('.inicio-nav');

  if (header) {
    header.appendChild(badge);
  } else if (nav) {
    nav.appendChild(badge);
  } else {
    badge.classList.add('user-badge--fixed');
    document.body.appendChild(badge);
  }
});
