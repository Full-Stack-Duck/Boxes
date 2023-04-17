import { useEffect, useState } from 'react'
import { Switch } from '@headlessui/react'

export function PersonSwitcher() {
  const [enabled, setEnabled] = useState(false)

  return (
    <div className='flex w-full justify-center'>
    <Switch
      checked={enabled}
      onChange={setEnabled}
      className={`${
        enabled ? 'bg-purple-medium' : 'bg-purple-light'
      } relative inline-flex h-6 w-11 items-center rounded-full`}
    >
      <span
        className={`${
          enabled ? 'translate-x-6' : 'translate-x-1'
        } inline-block h-4 w-4 transform rounded-full bg-white transition`}
      />
    </Switch>
    { !enabled && <label >Pessoa Física</label>}
    { enabled && <label >Pessoa Jurídica</label>}

    </div>
  )
}